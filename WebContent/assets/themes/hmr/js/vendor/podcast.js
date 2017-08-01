$(document).ready(function() {
	
	var base_url = $('#base_url').val()
	,	$dashMainContainer = $('#dash-main-container')
	,	$dashPageNav = $('#pages-navigation-wrap')
	,	$addPostForm = $('#add-post-form')
	,	is_query = 0
	,	somethingChanged = 0
	,	currentURL = new Url;

	var RTLPodCast = {
		init: function() {
			this.bindEvents();
			this.post();
			this.search();
			this.podcastAttachment();
		},
		bindEvents: function() {
			
		},

		search: function () {
			
			$('.post-search-form').on('submit', function (e) {
				e.preventDefault();
			 	var serializeArray = $(this).serializeArray()
				currentURL.query.q = serializeArray[0].value;
				delete currentURL.query.slug;
				if ( serializeArray[0].value == '' ) {
					delete currentURL.query.q;
				};
				window.location = currentURL.toString();
			});

		},

		post: function () {

			$('.podcast-form-wrap .form-control').live('change', function() { 
				somethingChanged = 1;
			});

			$addPostForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( is_query ) return;
				is_query = 1;

				$('.tab-has-error').removeClass('tab-has-error');

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					// $('body').append(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					is_query = 0;
					console.log(data)
					if ( data.is_valid ) {

						// MSPB2.submitContent(function (pbdata) {
						// 	console.log(pbdata)
						// 	if ( pbdata.is_valid ) {
						// 		// location.reload();
						// 		somethingChanged = 0;
						// 	};
						// });

						somethingChanged = 0;
						webArtJs.messenger({
							message: "Post has been saved!",
							closeAfter: 3000,
							position: 'top-left',
							closeBtn: 1,
						});

						setTimeout(function () {
							window.location = base_url + 'cms/podcast';
						}, 1000);

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
			            		var $navLi = $('#'+ $("#" + index).parents('.form-group').parents('.tab-pane').attr('id') + '-li' );
			            		$navLi.addClass('tab-has-error');;
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });

					}
					console.log("complete");
				});
					

			});

			$('.post-action').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('type') == "Save" ) {
					$addPostForm.submit();
				}

				if ( $this.data('type') == "Cancel" ) {
					
					if ( somethingChanged == 0 ){
						window.location = base_url + 'cms/podcast';
						return;
					}

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
					    	window.location = base_url + 'cms/podcast';
					    	// location.reload();
					      }
					    },
					  }
					});

				};

				console.log( $this.data('type') )

				if ( $this.data('type') == "delete" ) {

					bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this post?",
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
					    	RTLPodCast.setDelete($this.data('id'), function (data) {
					    		if ( data.is_valid ) {
					    			webArtJs.messenger({
										message: "Post has been deleted!",
										closeAfter: 3000,
										position: 'top-left',
										closeBtn: 1,
									});

									if ( $('#dash-stream-issue-'+ $this.data('id')).length !=0 ) {
										$('#dash-stream-issue-'+ $this.data('id')).remove();
									}else{
										window.location = base_url + 'cms/podcast';	
									}
									
					    		}else{
					    			webArtJs.messenger({
										message: "There's a problem deleting the post. Please try again later.",
										closeAfter: 3000,
										position: 'top-left',
										closeBtn: 1,
									});
					    		}
					    	});
					    	
					      }
					    },
					  }
					});

				}
				

				
			});



			$('#post_url').on('change', function () {
				$('#use_post_url').val(1);
			});

			RTLPodCast.tags();
			RTLPodCast.blogCategory();
			RTLPodCast.dateTimePicker();
			RTLPodCast.postImage();

		},

		setDelete: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/podcast/set_deleted',
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

		dateTimePicker: function() {
			
			if(!jQuery().datepicker) return;

			var $dateTimeText = $('#datetime-text');

			var $blogDatePicker = $('#ms-datepicker-picker').datepicker({
				format: 'yyyy-mm-dd'
			});

			var $postDate = $('#post_date');
			var formattedDate = $blogDatePicker.datepicker('getFormattedDate');

			$postDate.val( formattedDate )

			console.log(formattedDate)

			RTLPodCast.datePostedText(formattedDate);

			$blogDatePicker.on('changeDate', function(e) {
				var $this = $(this);
				formattedDate = $this.datepicker('getFormattedDate');
				var date_arr = formattedDate.split('-');

		        $postDate.val( formattedDate )
		        
		        console.log( formattedDate )	

		        // console.log( moment(formattedDate).calendar() )

		        RTLPodCast.datePostedText(formattedDate);

		    });


		    var time = '<span class="post-minutes-wrap">'+ $('#post-minutes').val() +'</span>' + ':' + '<span class="post-seconds-wrap">'+ $('#post-seconds').val() +'</span>' + ' <span class="post-meridian-wrap">'+ $('#post-meridian').val() +'</span>';

		    $('.datetime-pick').live('click', function(e) {
		    	e.preventDefault();
		    	$('#post-datetime-wrap').toggleClass('hide');
		    });

			console.log( $('#status').val() )

		    if ( $('#status').val() == "draft" ) {
		    	$dateTimeText.html("This post will not be visible.");
		    }
		    if ( $('#status').val() == "scheduled" ) {
		    	RTLPodCast.datePostedText(formattedDate);
		    }

		    
		    $('#status').on('change', function() {
		    	var $this = $(this);
		    	if ( $this.find('option:selected').val() == "published" ) {
		    		RTLPodCast.datePostedText(formattedDate);
		    	}
		    	if ( $this.find('option:selected').val() == "draft" ) {
		    		$dateTimeText.html("This post will not be visible.");
		    	}
		    	if ( $this.find('option:selected').val() == "scheduled" ) {
		    		RTLPodCast.datePostedText(formattedDate);
		    	}
		    });

		    $(document).click(function(e){
			    if ($(e.target).closest(".datetime-pick").length == 0 && $(e.target).closest(".ms-datetime-picker").length == 0 ) {
			        $('#post-datetime-wrap').addClass('hide');
			    }
			});

			$('#post-minutes').on('change', function() {
				var $this = $(this);
				if ( $this.val() > 12 ) {
					$this.val(12);
				}
				if ( $this.val() == 0 ) {
					$this.val(1)
				}

				if ( $this.val() < 10 ) {
					$this.val( '0' + parseInt($this.val()) );
				}

			}).on('keyup, change', function() {
				RTLPodCast.datePostedText(formattedDate);
			})

			$('#post-seconds').on('change', function() {
				var $this = $(this);
				if ( $this.val() > 59 ) {
					$this.val(59);
				}
				if ( $this.val() <= 0 ) {
					$this.val(00)
				}
			}).on('keyup, change', function() {
				var $this = $(this);
				if ( $this.val() < 10 ) {
					$this.val( '0' + parseInt($this.val()) );
				}
				RTLPodCast.datePostedText(formattedDate);
			})

			$('#post-meridian').on('change', function() {
				RTLPodCast.datePostedText(formattedDate);	
			});

		},

		datePostedText: function(formattedDate) {
			
			console.log("datePostedText")
			var $dateTimeText = $('#datetime-text');
			var now = moment().format('YYYY-MM-DD');

		    console.log(formattedDate)

	        var datePicked = moment( formattedDate );
	        var diff = parseInt(datePicked.diff(moment(now), 'days'));
	        var time = '<span class="post-minutes-wrap">'+ $('#post-minutes').val() +'</span>' + ':' + '<span class="post-seconds-wrap">'+ $('#post-seconds').val() +'</span>' + ' <span class="post-meridian-wrap">'+ $('#post-meridian').val() +'</span>';
	        
	        console.log( diff )

	        console.log( datePicked.format("D") )

	        if ( diff == 0 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> today at ' + time + '</a>');
	        }

	        if ( diff < 0 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> '+ datePicked.format("MMMM D, YYYY") +' at ' + time + '</a>');
	        }

	        if ( diff == 1 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> tomorrow at ' + time + '</a>');
	        }

	        if ( diff > 1 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> in <a href="#" class="datetime-pick"> '+ diff +' days at ' + time + '</a>');	
	        }

	        if ( diff >=1 ) {
	        	$('#status').val('scheduled');
	        }else{
	        	$('#status').val('published');
	        }
	        

	        if ( $('#status').val() == "publish" && diff < 0 ) {
				$dateTimeText.html( '<span class="post-stat-wrap">Published</span> on <a href="#" class="datetime-pick"> '+ moment(datePicked).format("MMM D") +' at ' + time + '</a>');	
			}
		},

		blogCategory: function () {
	      console.log("blogCategory")
	      var selectProgram = 0;

	      	if ( $('#category_id').length == 0 ) return;
			var $select = $('#category_id').selectize({
				create: true,
				createOnBlur: true,
			    // sortField: 'text',
			    onOptionAdd: function (value) {
			    	
			    	if ( selectProgram ) return;

			    	var control = $select[0].selectize;

			    	bootbox.dialog({
					  title: "Add Category Confirmation",
					  message: "Are you sure you wish to add " + value + " in category?",
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
					    	RTLPodCast.addNewCategory(value, function (data) {
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
		
	    },

	    addNewCategory: function (value, callback) {
	    	
	    	$.ajax({
	    		url: base_url + 'cms/blog/validate',
	    		type: 'POST',
	    		dataType: 'json',
	    		data: {
	    			title: value,
	    			body: '',
	    			type: 'blog_category',
	    			parent: currentURL.query.page_id,
	    			status: 'published',
	    		},
	    	})
	    	.fail(function(e) {
	    		console.log(e.responseText)
	    		console.log("error");
	    	})
	    	.always(callback);
	    		

	    },

	    tags: function () {
	    	
	  //   	console.log("tags")
	  //   	var selectProgram = 0;

	  //   	if ( $('#tags').length == 0 ) return;

	  //   	var $tags = $('#tags').selectize({
			// 	create: true,
			// 	createOnBlur: true,
			// 	plugins: ['remove_button', 'restore_on_backspace'],
			// });

			var $tagSearch = $('#tags');
			var existingTags = [];


			if ( $tagSearch.length == 0 ) return;
			
			if ( $tagSearch.hasClass('done') ) return;

			$tagSearch.kTypeAhead({
				url: base_url + 'cms/podcast/tag_suggest',
				data: {
				    type: 'Podcast Tag',
				    limit: 15,
				},
				delay: 400,
				searchBtn: '#search-btn',
				onEnter: function () {
				    console.log( $tagSearch.val() )

				    if ( $.inArray($tagSearch.val(), existingTags) == -1 ) {
				    	RTLPodCast.addTagItem( $tagSearch.val() )
				    	existingTags.push( $tagSearch.val() )
				    }
				    $tagSearch.val('');
				    
				    

				},
				onDoQuerySuggestions: function () {

				},
				onSuggestionClick: function () {
				    // if ( searchModal.hasClass('open') || searchResultWrapper.length !=0 ) {
				    //   searchForm.submit();
				    // };
				    console.log("suggestion click")
				    console.log( $tagSearch.val() )

				    if ( $.inArray($tagSearch.val(), existingTags) == -1 ) {
				    	RTLPodCast.addTagItem( $tagSearch.val() )
				    	existingTags.push( $tagSearch.val() )
				    }
				    $tagSearch.val('');

				    $('.kk-suggestions').removeClass('has-items').html('');
				},
				onEnterWithSelectedSuggestion: function () {
				    
				    $('.kk-suggestions').removeClass('has-items').html('');
				},
				onSuggestionOpen: function () {
				    // console.log("onSuggestionOpen");
				},
				onSuggestionClose: function () {
				    // console.log("onSuggestionClose");
				},
				onComplete: function (data) {
				    console.log(data)
				},
				onFail: function (e) {
				    console.log(e.responseText)
				}
			}).addClass('done');


			$('.tag-remove').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
				  title: "Delete Confirmation",
				  message: "Are you sure you wish to delete this tag?",
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

				      	if ( $this.data('id') ) {
				      		RTLPodCast.deleteTag( $this.data('id'), function(data) {
				      			if ( typeof data !="undefined" ) {
				      				$this.parents('.tag-item').remove();
				      			}
				      		});
				      	}else{
				      		$this.parents('.tag-item').remove();	
				      	}
				    	
				      }
				    },
				  }
				});

			});


			$.each($('.tag-item'), function(index, val) {
				var $this = $(this);
				existingTags.push($this.find('.tag-name').text());
			});

			console.log(existingTags);
			

	    },

	    deleteTag: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/podcast/delete_tag',
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

	    addTagItem: function(tag_name) {
	    	
	    	var $tagList = $('#podcast-tag-list');

	    	$tagList.append('<div class="tag-item">' +
						'<input type="hidden" name="tag_list[]" value="'+ tag_name +'">' +
						'<input type="hidden" name="tag_id[]">' +
						'<span class="tag-name">'+ tag_name +'</span>' +
						'<a href="#" class="tag-remove">×</a>' +
					'</div>');

	    },

	    postImage: function () {
			var	$dropElement = $('#image-wrapper')
			,	max_file_count = 1;

			if ( $dropElement.length == 0 ) return;

			var $currentImageWrap = $('#current-image-wrap');
			var $preloader = $('#post-image-preloader');
			var $postImagePickfile = $('#post-image-pickfile');

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'post-image-pickfile', // you can pass an id...
				drop_element: 'image-wrapper',
				dragdrop: true,
				container: document.getElementById('post-image-pickfiles-container'), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size: '100mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,jpeg,gif,png"},
					]
				},
				multi_selection: false,
				multipart_params : {
			        "attach_id" : currentURL.query.id,
			        "attachment_for" : "post_image",
			        "resize" : 1,
			    }
			});
			// console.log(uploader);

			uploader.init();

			// UploadProgress
			uploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
				
				
				console.log(file.percent)

				if ( file.percent == 100 ) {
					setTimeout(function () {
						$preloader.hide();
					}, 500)
				};
			});

			// FilesAdded
			uploader.bind('FilesAdded', function (up, files) {
			    console.log(files)
				
				if(up.files.length > max_file_count){
			        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
			        for(var i in files){
						uploader.removeFile(files[i]);
					};
			        return;
			    }

				for(var i in files){
					var file = files[i];
					$preloader.show().html('<div id="'+ file.id +'"><div class="media-preview file-detail files-added">'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></div>');
				};
				uploader.start();
				uploader.refresh();
			});

			// Error
			uploader.bind('Error', function (up, err) {
				console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$preloader.addClass('error-file').html('<div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div>');
				}else{
					$preloader.html('<div id="'+ err.file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div></div>');
				}
				uploader.refresh();
			});



			uploader.bind('FileUploaded', function (up, file, response) {
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)

				if ( jsonResponse.is_valid ) {
					$('.msis-error-message-wrapper').hide();

					$dropElement.addClass('has-image');
					$postImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Change Image');

					$('.post-image-remove').removeClass('hide');

					$('#post_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentImageWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ jsonResponse.image_path.medium  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$postImagePickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add an image');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});

			$('.post-image-remove').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this thumbnail?",
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
							
							$('#post_image').val('');

							$dropElement.removeClass('has-image');
							$postImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Add an image');
							$currentImageWrap.html('');
							$this.addClass('hide')
					    	
					      }
					    },
					  }
					});


				

			});



		},

		podcastAttachment: function() {
			
			if ( $('#podcast-pickfiles-container').length == 0 || $('#podcast-pickfiles-container').hasClass('uploader-init') ) return;

			var	$dropElement = $('#podcast-image-wrapper')
			,	max_file_count = 1;

			var $currentLogoWrap = $('#current-podcast-wrap');
			var $preloader = $('#podcast-preloader');
			var $podcastpickfile = $('#podcastpickfile');

			podcastUploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'podcastpickfile', // you can pass an id...
				drop_element: 'podcast-image-wrapper',
				dragdrop: false,
				container: document.getElementById('podcast-pickfiles-container'), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size: '100mb',
					mime_types: [
						{title : "Audio files", extensions : "mp3"},
					]
				},
				multi_selection: false,
				multipart_params : {
			        "attach_id" : 0,
			        "attachment_for" : "podcast",
			    }
			});
			// console.log(podcastUploader);

			podcastUploader.init();

			$('#podcast-pickfiles-container').addClass('uploader-init');

			// UploadProgress
			podcastUploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
				
				$preloader.show();

				// console.log(file.percent)

				if ( file.percent == 100 ) {
					setTimeout(function () {
						$preloader.hide();
					}, 500)
				};
			});

			// FilesAdded
			podcastUploader.bind('FilesAdded', function (up, files) {
			    // console.log(files)
				
				// if(up.files.length > max_file_count){
			 //        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
			 //        for(var i in files){
				// 		podcastUploader.removeFile(files[i]);
				// 	};
			 //        return;
			 //    }

				for(var i in files){
					var file = files[i];
					$preloader.hide().html('<div id="'+ file.id +'"><div class="media-preview file-detail files-added">'+ '<div class="progress progress-sm"><div class="progress-bar progress-bar-success progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></div>');
				};
				podcastUploader.start();
				podcastUploader.refresh();


				$('.msis-error-message-wrapper').hide();
				$currentLogoWrap.html( files[0].name );
				$podcastpickfile.html('<span class="ion-edit right5"></span> Change file');
			});

			// Error
			podcastUploader.bind('Error', function (up, err) {
				// console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$preloader.addClass('error-file').html('<div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div>');
				}else{
					$preloader.html('<div id="'+ err.file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div></div>');
				}
				podcastUploader.refresh();
			});



			podcastUploader.bind('FileUploaded', function (up, file, response) {
				var jsonResponse = $.parseJSON(response.response);

				// console.log(jsonResponse)

				if ( jsonResponse.is_valid ) {

					$('#podcast').parents('.form-group').removeClass('has-error').find('.help-block').text('');


					$('.msis-error-message-wrapper').hide();
					$podcastpickfile.html('<span class="ion-edit right5"></span> Change file');

					$('#podcast_path').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentLogoWrap.html( jsonResponse.upload_data.file_name );
					
					podcastUploader.removeFile(file.id);

				};

				if ( !jsonResponse.is_valid ) {
					$podcastpickfile.html('<span class="ion-plus-round right5"></span> Add a file');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					podcastUploader.refresh();
				};

			});

		},






	};


	RTLPodCast.init();

});		