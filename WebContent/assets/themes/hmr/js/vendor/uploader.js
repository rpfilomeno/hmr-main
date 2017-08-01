var msMedia;

jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	$dropElement = $('#media-drop-element')
	,	max_file_count = 100
	,	currentURL = new Url;

	var $filelist = $('#filelist');

	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'pickfiles', // you can pass an id...
		drop_element: 'media-drop-element',
		dragdrop: true,
		container: document.getElementById('pickfiles-container'), // ... or DOM Element itself
		url : base_url + 'cms/media/do_upload',
		flash_swf_url : base_url + 'js/vendor/Moxie.swf',
		silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
		filters : {
			max_file_size : ($dropElement.data('max-file-size')) ? $dropElement.data('max-file-size') : '100mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,jpeg,gif,png"},
				{title : "Compressed files", extensions : "tar,tgz,iso,zip,rar"},
				{title : "Document files", extensions : "psd,ai,eps,ps,ppt,word,doc,docx,xls,xlsx,pptx,csv,pdf,txt"},
				{title : "Video files", extensions : "avi,mpeg,mp3,mp4,3gp"},
			]
		},
		// multi_selection: false,
		// chunks: true,
		multipart_params : {
	        "attach_id" : 0,
	        "resize" : 1,
	        "attachment_for" : ( $filelist.data('attachment-for') ) ? $filelist.data('attachment-for') : "media_library",
	    }
	});
	// console.log(uploader);

	uploader.init();

	

	// PostInit
	uploader.bind('PostInit', function () {
		
		// $('#filelist').html('')
		// $('#uploadfiles').on('click', function (e) {
		// 	console.log("do upload")
		// 	uploader.start();
		// 	return false;
		// });
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
			$filelist.prepend('<li id="'+ file.id +'" class="selectable col-lg-3 col-md-4 col-xs-6"><div class="media-preview file-detail files-added"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></li>');
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
			$filelist.prepend('<li id="'+ err.file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6"><div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-warning">'+ err.message +'</div>' +'</div></li>');
		}
		uploader.refresh();
	});

	uploader.bind('FileUploaded', function (up, file, response) {
		var jsonResponse = $.parseJSON(response.response);

		console.log(jsonResponse)
		if ( jsonResponse.is_valid ) {
			$('.msis-error-message-wrapper').hide();
			$('#'+file.id).replaceWith(jsonResponse.file_preview);
		};
		if ( !jsonResponse.is_valid ) {
			$('#'+file.id).replaceWith('<li id="'+ file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6 error-file"><div class="media-preview file-detail"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>' + '<div class="text-warning">'+ jsonResponse.errors +'</div>' +'</div></li>');
		};

	});

	msMedia = {
		init: function () {
			this.bindEvents();
			this.msis();
			this.detail();
		},
		bindEvents: function () {
			this.toggleBox();
			this.actions();
			this.quickDelete();
			this.search();
			this.filter();
			// $('[data-spy="affix"]').affix();
		},
		actions: function () {

			$.each($('.action-bar'), function(index, val) {
				var $this = $(this);
				setTimeout(function () {
				}, 1000)
				$this.css('width', $this.parents('.dash-header').width());
			});

			$(window).on('resize', function () {
				$.each($('.action-bar'), function(index, val) {
					var $this = $(this);
					$this.css('width', $this.parents('.dash-header').width());
				});
			})
			
			$('.bulk-select-btn').on('click', function (e) {
				e.preventDefault();
				var $this = $(this)
				,	text = ( $this.text() == "Bulk Select" ) ? 'Cancel Selection' : 'Bulk Select';
				$this.text(text).blur();

				// console.log(text);
				if ( text == "Bulk Select" ) {
					// console.log("remove all selected");
					msMedia.removeAllSelected();
				}else{
					$('.delete-selected').parents('li').removeClass('hide')
					$('#filelist').addClass('ready-to-select')
				}

			});

			$('.delete-selected').on('click', function () {
				var $this = $(this)
				,	media_ids = [];

				if ( $('#filelist li.selected').length == 0 ) {
					bootbox.alert({
						// className: "my-modal",
						title: "Oooops!",
						message: "There are no selected media item",
					});
					return;
				}

				bootbox.confirm({
					title: "Delete Confirmation",
				    message: "Are you sure you want to delete all selected item?",
				    callback: function (result) {
				    	if ( result === true ) {
							$.each($('#filelist li.selected'), function(i, val) {
								var $self = $(this);
								media_ids.push( $self.find('input').val() )
							});
							
							// console.log(media_ids)
							msMedia.deleteItem(media_ids);
						}else{
							// cancel delete
							console.log("Delete has been cancelled");
						}
				    }
				}); 		
			});

		},

		removeAllSelected: function () {
			$('#filelist li').removeClass('selected');
			$('#filelist').removeClass('ready-to-select')
			$('.delete-selected').parents('li').addClass('hide')	
		},

		deleteItem: function (media_ids) {
			
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
						$('#media-'+ val).parents('li').remove();
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

		toggleBox: function () {
			$('.btn-show-drop-element').on('click', function (e) {
				e.preventDefault();
				this.blur()
				$dropElement.removeClass('hide');
				webArtJs.animate2ID(null, 0)
			});

			$('.dismiss-drop-element').on('click', function (e) {
				e.preventDefault();
				this.blur()
				$dropElement.addClass('hide');
				webArtJs.animate2ID(null, 0)
			})
		},
		msis: function () {
			
			var $fileList = $('#filelist').msis({
				ajaxOptions:{
					data: {
						ajax: 1,
						tpl: 'tpl',
						size: ( $('#filelist').data('size') ) ? $('#filelist').data('size') : 'medium',
						attachment_for: $('#filelist').data('attachment-for'),
						url_query: currentURL.query,
					}
				},
				errorMessage: '<strong>Oooops!</strong> There are no data to display.',
				errorClass: 'col-md-8 text-warning',
				loader_image: base_url + 'img/loader.gif',
				trigger_type: ($('#filelist').data('trigger-type')) ? $('#filelist').data('trigger-type') : "scroll",
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
					console.log(response);
					// console.log("doooonnnee");
					if ( typeof response != "undefined" && typeof response.total_rows_formatted != "undefined" ) {
						$('.msis-count').text(response.total_rows_formatted);
					};
					console.log("lazy")
				}
			});



		},

		detail: function () {
			
			var lastSelected = 0;
			$('.media-preview').live('click', function (e) {
				e.preventDefault();
				var $this = $(this)
				,	$liParent = $this.parents('li');

				if ( $('.bulk-select-btn').text() != 'Cancel Selection' ) {
					// get media item details
					msMedia.getDetails($this.data('id'));
				}else{
					// select media item
					if ( !$liParent.hasClass('selected') ) {
						$liParent.addClass('selected')
					}else{
						$liParent.removeClass('selected')
					}

					if ( e.shiftKey ) {
						
						$('.media-grid > li').slice( $liParent.index(), lastSelected + 1).addClass('selected');
						console.log("backward");
						
						$('.media-grid > li').slice( lastSelected, $liParent.index()).addClass('selected');
						console.log("forward");

						// $('.media-grid > li.selected').slice( $liParent.index(), lastSelected + 1).removeClass('selected');
						$liParent.addClass('selected')
					}else{
						lastSelected = $liParent.index();
					}
				}

			
			});

			// $('.media-grid > li').slice(4, 10).addClass('selected');

		},

		getDetails: function (id) {
			
			if ( id == null ) {
				return;
			};
			if ( $('#report-illustration-modal').length !=0 ) return;
			
			$.ajax({
				url: base_url + 'cms/media/q',
				type: 'POST',
				dataType: 'json',
				data: {
					id: id,
					ajax: 1,
					tpl: "modal_tpl",
					size: 'large'
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
				webArtJs.ajaxError();
			})
			.always(function(data) {
				console.log(data);

				if ( data != null && data.length !=0 && data.is_valid ) {
					bootbox.dialog({
						title: "Media Detail",
						size: "large",
						className: "media-modal",
						message: data.results,
						buttons: {
							dismiss: {
						      label: "Dismiss",
						      className: "btn-sm btn-default",
						    },
						    save: {
						      label: "Save",
						      className: "btn-sm btn-success",
						      callback: function() {
						        msMedia.quickSave();
						        return false;
						      }
						    }
						}
					});
				};

				console.log("complete");
			});
				
		},
		quickSave: function () {

			var $form = $('#media-quick-edit-form');
			if ( $form.length == 0 ) return;
			var form_data = $form.serializeArray();
			console.log(form_data);
			
			$.ajax({
				url: $form.attr('action'),
				type: 'post',
				dataType: 'json',
				data: form_data,
			})
			.fail(function(e) {
				console.log(e.responseText);
				console.log("error");
				webArtJs.ajaxError();
			})
			.always(function(data) {
				console.log(data);
				if ( data.is_valid ) {

					bootbox.hideAll();

					webArtJs.messenger({
						header: "Yeeey!",
						message: "Post has been save.",
						closeAfter: 3000,
						position: 'top-left notif-success',
					});
				}else{
					webArtJs.formErrors(data);
				}
				console.log("complete");
			});

		},

		quickDelete: function () {

			$('.quick-delete').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('id') ) {
					bootbox.confirm({
						title: "Delete Confirmation",
					    message: "Are you sure you want to delete this item?",
					    callback: function (result) {
					    	console.log(result);
					    	if ( result === true ) {
					    		msMedia.deleteItem([$this.data('id')]);
					    		bootbox.hideAll();
					    	}
					    }
					});
				};
			});
		},

		search: function () {
			
			$('#media-search-form').on('submit', function (e) {
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
			
			$('.view-type').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				if ( $this.data('type') == "Grid" ) {
					delete currentURL.query.v;
					delete currentURL.query.start;
				};
				if ( $this.data('type') == "List" ) {
					currentURL.query.v = "List";
					delete currentURL.query.start;
				};
				window.location = currentURL.toString();
			});

			$('#date_filter').on('change', function () {
				var optionSelected = $(this).find("option:selected").val();
				if ( optionSelected != '' ) {
					currentURL.query.date = optionSelected;
				}else{
					delete currentURL.query.date;
				}
				delete currentURL.query.start;
				window.location = currentURL.toString();
			});

			$('#type_filter').on('change', function () {
				var optionSelected = $(this).find("option:selected").val();
				if ( optionSelected != '' ) {
					currentURL.query.type = optionSelected;
				}else{
					delete currentURL.query.type;
				}
				delete currentURL.query.start;
				window.location = currentURL.toString();
			});

		}

	};

	msMedia.init();


});
