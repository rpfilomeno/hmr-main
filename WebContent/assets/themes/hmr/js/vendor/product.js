jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	max_file_count = 100
	,	currentURL = new Url
	,	is_query = 0
	,	variants1 = []
	,	variants2 = []
	,	variants3 = [];

	var MSProduct = {
		init: function () {
			this.bindEvents();
			this.orderTransaction();
			
		},

		bindEvents: function () {
			
			MSProduct.testForm();
			MSProduct.search();
			MSProduct.filter();
			MSProduct.addNew();
			MSProduct.priceInput();

			if ( currentURL.query.id != null && currentURL.query.page_id !=null) {
				MSProduct.addImages({
					attach_id: currentURL.query.id
				});

				MSProduct.post_product_msis();
				MSProduct.post_product_action();
			};

			console.log( currentURL.query )
		},

		orderTransaction: function() {
		
			$('#order-transaction-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					console.log(data)
					if ( data.is_valid ) {
						bootbox.dialog({
						  title: "Congratulations!",
						  message: "Order status has been updated.",
						  // size: "small",
						  buttons: {
						    dismiss: {
						      label: "Dismiss",
						      className: "btn-default btn-sm",
						      callback: function() {
						        location.reload()
						      }
						    },
						  }
						});

					}
					console.log("complete");
				});

			});

		},

		testForm: function() {
		
			$('#test-form').on('submit', function(e) {
				var $this = $(this);
				e.preventDefault();

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					console.log(data)
					console.log("complete");
				});
					

			});

		},

		priceInput: function () {
			
			if ( $.fn.autoNumeric ) {
            	$('.price-input').autoNumeric({
            		aSep: ''
            	});
        	};

		},

		search: function () {
			
			$('#product-search-form').on('submit', function (e) {
				e.preventDefault();
			 	var serializeArray = $(this).serializeArray()
				currentURL.query.q = serializeArray[0].value;
				if ( serializeArray[0].value == '' ) {
					delete currentURL.query.q;
				};
				window.location = currentURL.toString();
			});

		},

		filter: function () {
			
			$('#category_filter').on('change', function () {
				var optionSelected = $(this).find("option:selected").val();
				if ( optionSelected != '' ) {
					currentURL.query.category = optionSelected;
				}else{
					delete currentURL.query.category;
				}
				// delete currentURL.query.start;
				window.location = currentURL.toString();
			});

		},

		addNew: function () {

			$('.add-new-product-btn').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				console.log("click")

				if ( $this.data('parent') !=0 ) {
					
					if ( is_query ) return;
					is_query = 1;

					$.ajax({
						url: base_url + 'cms/products/anp',
						type: 'POST',
						dataType: 'json',
						data: {parent: $this.data('parent')},
					})
					.fail(function(e) {
						console.log(e.responseText)
						console.log("error");
					})
					.always(function(data) {
						is_query = 0;
						console.log(data)
						console.log("complete");
						
						if ( data.is_valid ) {
							window.location = base_url + 'cms/products/add?page_id=' + currentURL.query.page_id + '&id=' + data.id + '&add_new=1';
						}
					});
							
				};
			});

			var $addProductForm = $('#add-product-form');

			$addProductForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( is_query ) return;
				is_query = 1;

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					$('body').append(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					is_query = 0;
					console.log(data)
					if ( data.is_valid ) {

						webArtJs.messenger({
							message: "Product post has been saved!",
							closeAfter: 3000,
							position: 'top-left',
							closeBtn: 1,
						});

						// setTimeout(function () {
						// 	window.location = base_url + 'cms/products/add?page_id=' + currentURL.query.page_id + '&id=' + data.id;
						// }, 300);

					}else{

						webArtJs.messenger({
							header: "",
							message: 'Please check the required field/s',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						$('.form-group').removeClass('has-error');
						$('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });

					}
					console.log("complete");
				});
					

			});

			$('.product-post-action').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('type') == "publish" || $this.data('duplicate') ) {
					$('#post-action-status').val($this.data('type'));	
					$addProductForm.submit();
				}

				if ( $this.data('type') == "delete" ) {
					bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this?",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Cancel",
					      className: "btn-default btn-sm",
					      callback: function() {
					        
					      }
					    },
					    success: {
					      label: "Confirm",
					      className: "btn-primary btn-sm",
					      callback: function() {
					      	MSProduct.setDelete($this.data('id'))
					    	window.location = base_url + 'cms/products?page_id=' + currentURL.query.page_id;
					      }
					    },
					  }
					});
				}

				if ( $this.data('type') == "cancel" ) {
					
					bootbox.dialog({
					  title: "Cancel Confirmation",
					  message: "Are you sure you wish to leave this page without saving?",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Cancel",
					      className: "btn-default btn-sm",
					      callback: function() {
					        
					      }
					    },
					    success: {
					      label: "Confirm",
					      className: "btn-primary btn-sm",
					      callback: function() {
					    	window.location = base_url + 'cms/products?page_id=' + currentURL.query.page_id;
					      }
					    },
					  }
					});

				};
				

				
			});


			$('.variant_on_sale_cb').live('click', function(e) {
				var $this = $(this);

				if ( $this.prop('checked') ) {
					$this.parents('.checkbox').find('.variant_on_sale').val(1);
				}else{
					$this.parents('.checkbox').find('.variant_on_sale').val(0);
				}
			});


			MSProduct.productCategory();
			MSProduct.pricingAndVariants();

		},

		setDelete: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/products/set_deleted',
				type: 'POST',
				dataType: 'json',
				data: {is_ajax: 1, id: id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
				
		},

		pricingAndVariants: function () {

			var $addVariantWrap = $('#add-variants-wrap');
			var variantID = 'variant-1';
			var curOptionCount = 1;
			var option_name = "Size";

			if ( $('.variant-option-values').length != 1 ) {
				$.each($('.variant-option-values'), function(i, val) {
					var vID = i+1;
					MSProduct.variantOptionValues('variant-'+vID);
				});
				
			}else{
				MSProduct.variantOptionValues(variantID);
			}
			

			$('.add-another-variant-option').on('click', function (e) {
				e.preventDefault();

				curOptionCount++;
				if ( curOptionCount > 3 ) return;
				
				if ( curOptionCount == 1 ) { option_name = "Size"; variantID = 'variant-1'; textClass = "text-info"; };
				if ( curOptionCount == 2 ) { option_name = "Color"; variantID = 'variant-2'; textClass = "text-warning";};
				if ( curOptionCount == 3 ) { option_name = "Material"; variantID = 'variant-3'; textClass = "text-danger"; };

				var variantOptionTpl = '<div id="variants-row-'+ variantID +'" class="row">'+
										'<div class="col-sm-3">' +
											'<div class="form-group">' +
												'<input type="text" name="option_name[]" class="form-control variant-option-name" value="'+ option_name +'">' +
											'</div>' +
										'</div>' +
										'<div class="col-sm-7 '+ textClass +'">' +
											'<div class="form-group">' +
												'<input id="'+variantID+'" type="text" name="option_values[]" class="variant-option-values" placeholder="Separate options with a comma">' +
											'</div>' +
										'</div>' +
										'<div class="col-sm-2">'+
											'<a data-id="'+variantID+'" href="#" class="btn btn-sm btn-danger btn-remove-variant-row"><span class="glyphicon glyphicon-trash"></span></a>'+
										'</div>'+
									'</div>';

				$addVariantWrap.append(variantOptionTpl);
				

				MSProduct.variantOptionValues(variantID);

			});
			

			$('.btn-remove-variant-row').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				$('#variants-row-'+ $this.data('id')).unbind().remove();

				if ( $this.data('id') == "variant-1" ) {
					variants1 = [];
				};
				if ( $this.data('id') == "variant-2" ) {
					variants2 = [];
					curOptionCount = 1;
				};
				if ( $this.data('id') == "variant-3" ) {
					variants3 = [];
					curOptionCount = 2;
				};
				
				var variants = MSProduct.variantCombinations([variants1, variants2, variants3]);
				MSProduct.appendVariantOptions(variants)
			});
			


			

		},

		variantOptionValues: function (option_value_input) {
			
			var v1 = $('#variant-1').val();
			var v2 = $('#variant-2').val();
			var v3 = $('#variant-3').val();

			if ( v1 !=null ) { variants1 = v1.split(','); };
			if ( v2 !=null ) { variants2 = v2.split(','); };
			if ( v3 !=null  ) { variants3 = v3.split(','); };
			

			var $thisOption = $('#'+option_value_input);

			if ( $thisOption.hasClass('selectize-added') ) return;

			$thisOption.addClass('selectize-added');

			$thisOption.selectize({
				plugins: ['remove_button'],
			    delimiter: ',',
			    persist: false,
				create: true,
				createOnBlur: true,
				onChange: function (value) {
					var array = value.split(',');
					console.log(array)
					if ( option_value_input == "variant-1" ) {
						variants1 = array;
					};
					if ( option_value_input == "variant-2" ) {
						variants2 = array;
					};
					if ( option_value_input == "variant-3" ) {
						variants3 = array;
					};


					
					var variants = MSProduct.variantCombinations([variants1, variants2, variants3]);
					MSProduct.appendVariantOptions(variants)
				},
			    onOptionAdd: function (value) {
			    	console.log(value)

			    	var control = $thisOption[0].selectize;
			    	console.log( control.getValue() )
			    	
			    },
			    onOptionRemove: function (value) {
			    	console.log(value)

			    	// var control = $select[0].selectize;
			    	// control.focus();
			    },

			});
			

		},

		appendVariantOptions: function (variants) {
			
			$('#pricing-variants-tbody').html('');

			

			$.each(variants, function(index, val) {

				console.log(val)

				if ( typeof val == "undefined" ) return;

				var textVal = '';
				var textTpl = '';

				$.each(val, function(i, v) {
					if ( typeof v != "undefined" ) {
						var text = $('<div/>',{ text: v }).html(); // MAKE SURE THAT THE VALUE IS TEXT ONLY
						var textClass = "text-info";

						textVal += text + ',';

						if ( i == 0 ) { textClass = "text-info"; };
						
						if ( i == 1 ) {textClass = "text-warning"; };

						if ( i == 2 ) {textClass = "text-danger"; };

						textTpl += '<li class="'+ textClass +'">'+ text  +'</li>';
					};					
				});

				var is_checked = ( $('#on_sale').prop('checked') ) ? 'checked' : '';
				var is_checked_val = ( $('#on_sale').prop('checked') ) ? 1 : 0;

				var productVariantTpl = '<tr>' +
											'<td>'+
												
											'</td>' +
											'<td class="col-sm-2 strong"><input name="variant_combination[]" type="hidden" value="'+ textVal.slice(0, -1) +'" /><ul class="pad0">'+ textTpl +'</ul></td>' +
											'<td>' +
												'<div class="form-group">' +
													'<input name="variant_price[]" type="text" value="'+ $('#price').val() +'" class="form-control price-input" placeholder="0.00">' +
												'</div>' +
											'</td>' +
											'<td>' +
												'<div class="form-group">' +
													'<input name="variant_sale_price[]" type="text" value="'+ $('#sale_price').val() +'" class="form-control price-input" placeholder="0.00">' +
												'</div>' +
											'</td>' +
											'<td>' +
												'<div class="form-group">' +
													'<div class="checkbox">'+
													    '<input class="variant_on_sale" type="hidden" name="variant_on_sale[]" value="'+ is_checked_val +'">' +
													    '<input '+ is_checked +' type="checkbox" value="1" class="variant_on_sale_cb">' +
													    '<label></label>' +
													    '<div class="help-block"></div>' +
													'</div>'+
												'</div>'+
											'</td>' +
											'<td>' +
												'<div class="form-group">' +
													'<input name="sku[]" type="text" class="form-control" placeholder="SKU">' +
												'</div>' +
											'</td>' +
											'<td>' +
												'<div class="form-group">' +
													'<input name="inventory[]" min="0" value="'+ $('#inventory').val() +'" type="number" class="form-control" placeholder="Inventory">' +
												'</div>' +
											'</td>' +
										'</tr>';

				$('#pricing-variants-tbody').append( productVariantTpl );

				MSProduct.priceInput();

				// console.log(val.join(' • ').slice(0, -3));
			});

		},

		variantCombinations: function (input) {	
			
			// Arbitrary base x number class 
			var BaseX = function(initRadix){
			    this.radix     = initRadix ? initRadix : 1;    
			    this.value     = 0;
			    this.increment = function(){
			        return( (this.value = (this.value + 1) % this.radix) === 0);
			    }
			}


			var output    = [], // Array containing the resulting combinations
		        counters  = [], // Array of counters corresponding to our input arrays
		        remainder = false, 
		        temp;
		    
		    // Initialize the counters
		    for( var i = input.length-1; i >= 0; i-- ){
		        counters.unshift(new BaseX(input[i].length));
		    }
		    
		    // Get all possible combinations
		    // Loop through until the first counter rolls over
		    while( !remainder ){
		        temp      = [];   // Reset the temporary value collection array
		        remainder = true; // Always increment the last array counter
		        
		        // Process each of the arrays
		        for( i = input.length-1; i >= 0; i-- ){
		            temp.unshift(input[i][counters[i].value]); // Add this array's value to the result
		            
		            // If the counter to the right rolled over, increment this one.
		            if( remainder ){
		                remainder = counters[i].increment();
		            }
		        }
		        output.push(temp); // Collect the results.
		    }
		    
		    return output;

			// Input is an array of arrays
			

		},

		addImages: function (params) {
			
			var $filelist = $('#add-image-wrapper');

			var attachment_for = 'Product';
			var attach_id = ( params.attach_id ) ? params.attach_id : 0;
			var drop_element = 'product-drop-element';
			var $dropElement = $('#'+drop_element);
			var pickfilesBtn = 'product-pickfiles';
			var pickfilesContainer = 'product-pickfiles-container';

			var mime_types = [
				{title : "Image files", extensions : "jpg,jpeg,gif,png"},
			];

			var server_params = {
				"attach_id" : attach_id,
				"attachment_for" : attachment_for,
				"no_file_preview" : 1,
				"resize" : 1,
		    };

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : pickfilesBtn, // you can pass an id...
				drop_element: drop_element,
				dragdrop: true,
				container: document.getElementById(pickfilesContainer), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size : ($dropElement.data('max-file-size')) ? $dropElement.data('max-file-size') : '100mb',
					mime_types: mime_types
				},
				// multi_selection: false,
				// chunks: true,
				multipart_params : server_params
			});

			uploader.init();

			

			// PostInit
			uploader.bind('PostInit', function () {
				
			});

			// UploadProgress
			uploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
			});

			// FilesAdded
			uploader.bind('FilesAdded', function (up, files) {
				console.log(up.files.length)
				if(up.files.length > max_file_count){
			        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
					for(var i in files){
						uploader.removeFile(files[i]);
					};
			        return;
			    }


				for(var i in files){
					var file = files[i];
					$filelist.append('<li id="'+ file.id +'" class="selectable col-lg-3 col-md-4 col-xs-6 col-small-gutter"><div class="media-preview file-detail files-added">'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></li>');
				};
				uploader.start();
				uploader.refresh();
			});


			// Error
			uploader.bind('Error', function (up, err) {
				// console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$('#'+ err.file.id).addClass('error-file').html('<div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-warning">'+ err.message +'</div>' +'</div>');
				}else{
					$filelist.append('<li id="'+ err.file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6 col-small-gutter"><div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-warning">'+ err.message +'</div>' +'</div></li>');
				}
				uploader.refresh();
			});

			uploader.bind('FileUploaded', function (up, file, response) {
				console.log(response)
				
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)
				if ( typeof jsonResponse !="undefined" && jsonResponse.is_valid ) {
					$('.msis-error-message-wrapper').hide();
					var image_pathNormal = base_url + jsonResponse.upload_data.upload_full_path + '/'+ jsonResponse.upload_data.raw_name + '-510-825' + jsonResponse.upload_data.file_ext;
					var image_pathFull = base_url + jsonResponse.upload_data.upload_full_path + '/'+ jsonResponse.upload_data.raw_name + jsonResponse.upload_data.file_ext;
				
					$('#'+file.id).replaceWith('<li id="post-product-'+jsonResponse.upload_data.media_id+'"><div class="post-product-controls"> <div class="btn-toolbar" > <div class="btn-group"> <button type="button" data-media-id="'+ jsonResponse.upload_data.media_id +'" data-action="edit" class="btn btn-xs btn-primary post-product-action" rel="tooltip" title="Edit Metadata"><span class="glyphicon glyphicon-pencil"></span></button> <button type="button" data-media-id="'+ jsonResponse.upload_data.media_id +'" data-action="delete" class="btn btn-xs btn-danger post-product-action" rel="tooltip" title="Delete"><span class="glyphicon glyphicon-trash"></span></button> </div> </div> </div><div class="media-preview" id="media-'+ jsonResponse.upload_data.media_id +'" data-id="'+ jsonResponse.upload_data.media_id +'"> <div class="media-thumbnail"> <div class="media-item margin-auto"> <img data-full-path="'+ image_pathFull +'" src="'+ image_pathNormal +'" class="product-image img-responsive animated fadeIn"> </div> </div> </div> </li>');

					MSProduct.doPostImageSort()

					var olArray = $('#add-image-wrapper').nestedSortable('toArray');
					// console.log(olArray)
					MSProduct.savePhotoOrder(olArray);
					// $('#'+file.id).replaceWith(jsonResponse.preview)

					// var featuredPath = $('#add-image-wrapper li').eq(0).find('.product-image').data('full-path');
					// $('#product-featured-photo').html( '<img class="img-responsive margin-auto animated fadeIn" src="'+featuredPath+'" />' )
					
				};
				if ( !jsonResponse.is_valid ) {
					$('#'+file.id).replaceWith('<li id="'+ file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6 col-small-gutter error-file"><div class="media-preview file-detail"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>' + '<div class="text-warning">'+ jsonResponse.errors +'</div>' +'</div></li>');
				};

			});

			
			$dropElement.on({
			    "dragenter": function(){
			        $(this).addClass('mouse_over');
			    },
			    "mouseleave drop": function(){
			        $(this).removeClass('mouse_over');
			    }
			});

		},

		post_product_msis: function () {
			
			var $fileList = $('#add-image-wrapper').msis({
				ajaxOptions:{
					data: {
						ajax: 1,
						tpl: 'tpl',
						size: 'normal',
						attach_id: currentURL.query.id,
					}
				},
				errorMessage: '',
				errorClass: 'col-md-8',
				loader_image: base_url + 'img/loader.gif',
				trigger_type: "button",
				// load_more_class: "btn btn-default",

				created: function (el) {
					// console.log(el.attr('id'))

					// el.trigger('get_contents');
				},
				responseError: function (e, el, response) {
					// console.log(response)
				},
				done: function (e, el, response) {
					// console.log(el);
					// console.log(response);
					// console.log("doooonnnee");
					if ( typeof response != "undefined" && typeof response.total_rows_formatted != "undefined" ) {
						$('.msis-count').text(response.total_rows_formatted);
					};

					MSProduct.doPostImageSort()
					
				}
			});

		},

		post_product_action: function () {
			
			$('.post-product-action').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('action') == "edit" ) {

				};
				if ( $this.data('action') == "delete" ) {
					bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete the photo?",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Cancel",
					      className: "btn-default btn-sm",
					      callback: function() {
					        
					      }
					    },
					    success: {
					      label: "Confirm",
					      className: "btn-primary btn-sm",
					      callback: function() {
					    	MSProduct.deleteProductImage([$this.data('media-id')]);

					      }
					    },
					  }
					});
					
				};


			});

		},

		deleteProductImage: function (media_ids) {
			
			if ( media_ids == null && media_ids.length == 0 ) return;

			console.log(media_ids);
			$.ajax({
				url: base_url + 'cms/media/delete',
				type: 'POST',
				dataType: 'json',
				data: {
					ajax: 1,
					media_ids: media_ids,
				},
			})
			.fail(function(e) {
				console.log(e.responseText);
				console.log("error");
			})
			.always(function(data) {
				console.log(data);
				if ( data !=null && data.length !=0 && data.is_valid ) {
					
					// remove all deleted on the list
					$.each(media_ids, function(index, val) {
						$('#post-product-'+ val).remove();
					});

					webArtJs.messenger({
						message: "Media Item/s has been deleted.",
						closeAfter: 3000,
						position: 'top-left',
						closeBtn: 1,
					})
				}else{
					webArtJs.messenger({
						header: "Oooops!",
						message: "Error deleting the file.",
						closeAfter: 3000,
						position: 'top-left notif-danger',
						closeBtn: 1,
					});
				}
				// console.log("complete");
			});	
		},

		doPostImageSort: function () {
			
			if ( typeof $.fn.nestedSortable == 'undefined' ) return;

	    	if ( $('#add-image-wrapper').length == 0 ) return;

			$('#add-image-wrapper').nestedSortable({
				// handle: 'div',
				items: 'li',
				// toleranceElement: '> div',
				maxLevels: 1,
				relocate: function () {
					// alert("relocate")
					var olArray = $('#add-image-wrapper').nestedSortable('toArray');
					// console.log(olArray)
					MSProduct.savePhotoOrder(olArray);
				}
			}); 

		},

		savePhotoOrder: function (olArray) {
			
			console.log(olArray)
			$.ajax({
				url: base_url + 'cms/products/save_photo_order',
				type: 'POST',
				dataType: 'json',
				data: {
	            	photos: olArray,
	            },
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( data.is_valid ) {
					webArtJs.messenger({
						header: "",
						message: 'Product photo order has been saved',
						closeAfter: 3000,
						position: "small top-left",
						closeBtn: true,
					});
				};

				console.log("complete");
			});
				

		},

		productCategory: function () {
	      console.log("productCategory")
	      var selectProgram = 0;

			var $select = $('#product_category').selectize({
				create: true,
				createOnBlur: true,
			    // sortField: 'text',
			    onOptionAdd: function (value) {
			    	
			    	if ( selectProgram ) return;

			    	var control = $select[0].selectize;

			    	bootbox.dialog({
					  title: "Add Category Confirmation",
					  message: "Are you sure you wish to add " + value + " in product category?",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Cancel",
					      className: "btn-default btn-sm",
					      callback: function() {
					      	control.removeOption(value, true)
					      	control.refreshItems()
				      		control.focus()
					      }
					    },
					    success: {
					      label: "Confirm",
					      className: "btn-primary btn-sm",
					      callback: function() {
					    	console.log("add "+ value + " to DB")
					    	MSProduct.addNewCategory(value, function (data) {
					    		console.log(data)
					    		if ( data.is_valid ) {			
					    			selectProgram = 1;
							      	control.removeOption(value, true)
							      
					    			control.addOption({
										value: data.id,
										text: value,
									});
									control.addItem(data.id);
									control.refreshItems();

									selectProgram = 0;
					    		};
					    	});
					      }
					    },
					  }
					});
			    },
			    onOptionRemove: function (value) {
			    	console.log(value)

			    	// var control = $select[0].selectize;
			    	// control.focus();
			    }

			});
			
			

	    	

			// $('#product_category2').selectize({
			// 	create: true,
			// 	plugins: ['remove_button', 'drag_drop'],
			// 	persist: false,
			// })
	    },

	    addNewCategory: function (value, callback) {
	    	
	    	$.ajax({
	    		url: base_url + 'cms/products/validate',
	    		type: 'POST',
	    		dataType: 'json',
	    		data: {
	    			title: value,
	    			body: '',
	    			type: 'Category',
	    			parent: currentURL.query.page_id,
	    			status: 'publish',
	    		},
	    	})
	    	.fail(function(e) {
	    		console.log(e.responseText)
	    		console.log("error");
	    	})
	    	.always(callback);
	    		

	    }




	};

	MSProduct.init();

});
