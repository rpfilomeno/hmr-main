jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	$pageTypeModal = $('#add-new-page-modal')
	,	$pageNavList = $('#pages-nav-list')
	,	$pagePreviewWrapper = $('#page-preview-wrapper')
	,	$pagePreviewBody = $('#page-preview-body')
	,	tinymceData = {
			currentParentWrap: ''
	}
	, 	designIsQuery = 0
	,	curPageData
	,	currentURL = new Url
	,	MSPB = {
		init: function () {
			this.bindEvents();
		},

		bindEvents: function () {
			this.pageAction();
			this.pageType();
			this.doSort();
			this.design();
			
			this.pageThumb();
			this.pagePreview();
			this.windowOnResize();
			this.windowHeight();
		},

		windowOnResize: function () {
			
			$(window).on('resize', function () {
				MSPB.windowHeight();
			});

		},

		windowHeight: function () {
			// console.log($(window).height());

			// console.log($('.wh').length)
			$.each( $('.wh'), function(index, val) {
				var $this = $(this);
				var offset = ($this.data('offset')) ? parseInt($this.data('offset')) : 0;

				$this.height($(window).height() - offset);
			});
		},

		pageThumb: function () {
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
			        "attachment_for" : "page_image",
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

					$('#page_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentImageWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ jsonResponse.image_path.medium  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$postImagePickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add a logo');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});



		},

		pagePreview: function () {
			
			// console.log(currentURL.query.slug)
			var html = '';
			var iframeUrl = base_url;

			if ( currentURL.query.pt =="Products" || currentURL.query.pt =="homepage" || currentURL.query.pt =="Page" ) {
				iframeUrl = base_url + currentURL.query.slug + '?edit=1&id='+ currentURL.query.id;	
			};

			if ( currentURL.query.pt == "Products" ) {
				iframeUrl = base_url + 'cms/products' + '?page_id='+ currentURL.query.id;
			};

			if ( currentURL.query.pt == "Blog" ) {
				iframeUrl = base_url + 'cms/blog' + '?page_id='+ currentURL.query.id;
			};

			if ( currentURL.query.pt == "Event" ) {
				iframeUrl = base_url + 'cms/blog' + '?page_id='+ currentURL.query.id;
			};

			html = '<iframe id="content-iframe" class="wh" data-offset="60" src="'+ iframeUrl +'" style="width: 100%;" frameborder="0"></iframe>';
		
			$('#page-preview-injection-point').html(html);
			MSPB.windowHeight();
			MSPB.windowOnResize();




			// var $iframe = $('#content-iframe').contents();

			// console.log( $iframe.find(".btn-cancel-content").attr('id') )

			// $iframe.find(".btn-cancel-content").live('click', function(){
			//    alert("test from pb");
			// });


			$('#content-iframe').load(function(){

		        var iframe = $('#content-iframe').contents();

		        iframe.find(".btn-cancel-content").click(function(){
		        	setTimeout(function() {
		        		$('body').addClass('dmc-open');
		        	}, 500)
		        });

		        iframe.find(".content-element-list > li > a").click(function(){
		            setTimeout(function() {
		            	$('body').addClass('dmc-open');
		            }, 500)
		        });

		        iframe.find(".edit-block").click(function(){
		            setTimeout(function() {
		            	$('body').addClass('dmc-open');
		            }, 500)
		        });

			});

			// $('.btn-cancel-content').on('click', function() {

			// 	console.log("click from iframe")

			
			// })


		},

		contentTools: function () {
		
			
			var options = {
			    valueNames: [ 'element-label' ]
			};

			var hackerList = new List('content-blocks-wrap', options);
			$('.content-element-wrapper').isolatedScroll();


			$('.get-text').on('click', function (e) {
				e.preventDefault();

				var html = $.parseHTML(tinymce.activeEditor.getContent());

				console.log( tinymce.activeEditor.getContent() )
				// console.log( tinymce.activeEditor.ControlSelection() )

				console.log(tinymce.activeEditor.selection)
				console.log(tinymce.activeEditor.selection.getNode())

				tinymce.remove("div.editable");
			});

			

		},

		contentTiny: function () {
			
			tinymce.init({
				plugins: "autoresize",
				setup: function (ed) {
					console.log(ed)	
	
					ed.on('change', function(e) {
			            // console.log('change event', e);
			            // ed.execCommand('mceAutoResize');
			        });

			        // ed.on('ObjectSelected', function(e) {
			        //     console.log('ObjectSelected event', e);
			        // });

			        ed.on('NodeChange', function(e) {
			        	// console.log("NodeChange")
			        	// console.log(e)
			        	var offset = $(e.element).position();
			        	var $currentParentWrap = $(e.element).parents('div');

			        	tinymceData.currentParentWrap = $currentParentWrap.data('type');
			        	// console.log(tinymceData)
			            console.log(offset);

			            $('.add-content-wrap').addClass('fadeIn').css({
			            	top: offset.top + 40,
			            	// left: offset.left,
			            });

			        });

				},
			    selector: ".editable",
			    inline: true,
			    fixed_toolbar_container: "#mytoolbar",
			    plugins: [
			        "advlist autolink lists link image charmap print preview anchor",
			        "searchreplace visualblocks code fullscreen",
			        "insertdatetime media table contextmenu paste"
			    ],
			    menubar: false,
			    theme: "modern",
			    skin: 'light',
			    height: 500,
			    autoresize_min_height: 500,
			    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
			});

		},

		pageAction: function () {

			// setTimeout(function () {
			// 	$('#toggle-dash-menu').click();
			// }, 2000);

			$('.btn-page-setdl').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.confirm({
					title: "Delete Confirmation",
				    message: "Are you sure you want to delete this page?",
				    callback: function (result) {
				    	if ( result === true ) {
							MSPB.setdl($this.data('id'), 'deleted', $this.data('permanent'), function (data) {
								if ( data.is_valid ) {
									webArtJs.messenger({
										header: "",
										message: 'Page has been deleted!',
										closeAfter: 3000,
										position: "small top-left",
										closeBtn: true,
									});

									setTimeout(function () {
										window.location = base_url + 'cms/pages';
									}, 1000);

								}else{
									webArtJs.messenger({
										header: "",
										message: 'Something went wrong, please try again later.',
										closeAfter: 3000,
										position: "small top-left",
										closeBtn: true,
									});
									// window.reload();
								}
							});

						}else{
							// cancel delete
							console.log("Delete has been cancelled");
						}
				    }
				}); 		
								
			});

			$('#page-settings-form').on('submit', function (e) {
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
						webArtJs.messenger({
							header: "",
							message: 'Page settings has been saved!',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						setTimeout(function () {
							window.location = base_url + 'cms/pages?id='+ data.id + '&slug=' + data.post_data.slug + '&pt=' + data.post_data.type;
						}, 1000);

					}else{
						webArtJs.messenger({
							header: "",
							message: 'Something went wrong, please try again later.',
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

			MSPB.folderNav();
			MSPB.pageTypeModal();

		},

		pageTypeModal: function () {
			// $pageTypeModal.modal('hide')
			$('.btn-folder-add-page').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				$('#page-parent-id').val(0);
				if ( $this.data('type') == "Folder" && $this.data('parent') ) {
					$pageTypeModal.modal('show')

					$('#page-folder-type').addClass('hide');
					$('#page-parent-id').val($this.data('parent'));
				};

			});

			$pageTypeModal.on('hidden.bs.modal', function (e) {
				$('#page-folder-type').removeClass('hide');
			});

		},

		folderNav: function () {
			$.each($('.toggle-folder-nav'), function(index, val) {
				var $this = $(this);
				// console.log($this)
				if ( $this.parents('li').hasClass('active') ) return;
				$($this.attr('href')).slideUp();
				$this.parents('li').find('.folder-nav-indicator').addClass('rotate180');
			});

			$('.toggle-folder-nav').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				$this.parents('li').toggleClass('active');

				$($this.attr('href')).slideToggle();
				$this.parents('li').find('.folder-nav-indicator').toggleClass('rotate180');
			});	
		},

		pageType: function () {
			
			$('#page-type-list-box > li > a').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				$pageTypeModal.modal('hide')
				$pageNavList.find('li').removeClass('active');
				MSPB.addPageListItem(null, $this.data('type'), "New " + $this.data('type'), 1);
				$('[data-toggle="tooltip"]').tooltip();

				setTimeout(function () {
					$('.form-page-list').focus().select();
				}, 500)
			});

			$('.form-page-list').live('keypress', function (e) {
				
			});

			$('#add-new-page-form').live('submit', function (e) {
				e.preventDefault();
				var $this = $(this)
				
				MSPB.pageSubmit($this.serializeArray(), function (data) {
					console.log(data)
					if ( data.is_valid ) {
						data.post_data.page_id = data.id;
						curPageData = data.post_data;

						if ( data.is_valid ) {
							$pageNavList.find('li.active').remove();
							$('#add-new-page-form').remove();

							// if ( data.post_data.type == "Folder" ) {
							// 	window.location = base_url + 'cms/pages?id=' + data.id;
							// };
							currentURL.query.id = curPageData.page_id;
							currentURL.query.parent = curPageData.parent;
							currentURL.query.slug = curPageData.slug;
							currentURL.query.pt = curPageData.type;

							var page_url = base_url + 'cms/pages?id=' + curPageData.page_id + '&parent=' + curPageData.parent + '&slug=' + curPageData.slug + '&pt=' + curPageData.type;
							MSPB.addPageListItem(data.id, data.post_data.type, data.post_data.title, null, 'append', page_url);
							$('#page-parent-id').val(0);
							
							MSPB.doSort();
							var olArray = $('#pages-nav-list').nestedSortable('toArray');
							MSPB.saveReOrder(olArray);
						};
					};
				});

			});

		},

		addPageListItem: function (id, type, name, form, method, url) {
			
			var item = MSPB.render('page-list-sidenav',{
				id: id,
				name: name,
				type: type,
				form: form,
				parent: $('#page-parent-id').val(),
				url: url,
			});

			console.log($('#page-parent-id').val())

			if ( method == null || method == "prepend" ) {
				if ( $('#page-parent-id').val() !=0 ) {
					$('#folder-nav-'+$('#page-parent-id').val()).prepend(item);
				}else{
					$pageNavList.prepend(item);
				}
				
			}

			if ( method == "append" ) {
				if ( $('#page-parent-id').val() !=0 ) {
					$('#folder-nav-'+$('#page-parent-id').val()).append(item);
				}else{
					$pageNavList.append(item);
				}
			}

		},

		pageSubmit: function (data, callback) {
			
			$.ajax({
				url: base_url + 'cms/pages/add_new_page',
				type: 'POST',
				dataType: 'json',
				data: data,
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
					

		},

		doSort: function () {

			if ( typeof $.fn.nestedSortable == 'undefined' ) return;

	    	if ( $('.page-sort').length == 0 ) return;

	    	$.each($('.page-sort'), function(index, val) {
	    		var $this = $(this);
	    		$('#'+ $this.attr('id')).nestedSortable({
					// handle: 'div',
					items: 'li.parent-page',
					// toleranceElement: '> div',
					maxLevels: 1,
					relocate: function () {
						// alert("relocate")
						var olArray = $('#'+ $this.attr('id')).nestedSortable('toArray');
						MSPB.saveReOrder(olArray);
					}
				}); 	
	    	});

			

			$.each($('.folder_pages_navigation'), function(index, val) {
				var $this = $(this);
				$('#'+ $this.attr('id')).nestedSortable({
					// handle: 'div',
					items: 'li',
					// toleranceElement: '> div',
					maxLevels: 1,
					relocate: function () {
						// alert("relocate")
						var olArray = $('#'+ $this.attr('id')).nestedSortable('toArray');
						MSPB.saveReOrder(olArray);
					}
				}); 
			});
		},

		saveReOrder: function (olArray) {
			
			console.log(olArray)
			$.ajax({
				url: base_url + 'cms/pages/save_nav_order',
				type: 'POST',
				dataType: 'json',
				data: {
	            	pages: olArray,
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
						message: 'Main navigation order has been saved',
						closeAfter: 3000,
						position: "small top-left",
						closeBtn: true,
					});
				};


				MSPB.pagePreview();
				console.log("complete");
			});
				

		},

		setdl: function (id, status, permanent, callback) {
			
			$.ajax({
				url: base_url + 'cms/pages/setdl',
				type: 'POST',
				dataType: 'json',
				data: {id: id, permanent: permanent, status: status},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
				
		},

		design: function () {
			

			$('#design-nav-list > li > a').on('click', function (e) {
				e.preventDefault();
				var $this = $(this)
				MSPB.getDesignForm($this.data('form'), $this.text());
			});

			$('.close-design-form-btn').live('click', function (e) {
				e.preventDefault();
				MSPB.CloseMediumWPageNav();
			});

			$('#logo-title-form').live('submit', function (e) {
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
						webArtJs.messenger({
							header: "",
							message: 'Logo and Title has been saved.',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});
					};
					console.log("complete");
				});
						
			});

		},

		designLogoUpload: function () {
			var	$dropElement = $('#logo-image-wrapper')
			,	max_file_count = 1;

			var $currentLogoWrap = $('#current-logo-wrap');
			var $preloader = $('#logo-preloader');
			var $logopickfile = $('#logopickfile');

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'logopickfile', // you can pass an id...
				drop_element: 'logo-image-wrapper',
				dragdrop: true,
				container: document.getElementById('logo-pickfiles-container'), // ... or DOM Element itself
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
			        "attach_id" : 0,
			        "attachment_for" : "sitelogo",
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
					$logopickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Change logo');

					$('#logo_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentLogoWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ base_url + jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					$('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$logopickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add a logo');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});



		},

		designFaviconUpload: function () {
			var	$dropElement = $('#favicon-wrapper')
			,	max_file_count = 1;

			var $currentLogoWrap = $('#current-favicon-wrap');
			var $preloader = $('#favicon-preloader');
			var $faviconpickfile = $('#faviconpickfile');

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'faviconpickfile', // you can pass an id...
				drop_element: 'favicon-wrapper',
				dragdrop: true,
				container: document.getElementById('favicon-pickfiles-container'), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size: '100mb',
					mime_types: [
						{title : "Image files", extensions : "png,ico,icon"},
					]
				},
				multi_selection: false,
				multipart_params : {
			        "attach_id" : 0,
			        "attachment_for" : "sitefavicon",
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
					$faviconpickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Change logo');

					$('#favicon').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentLogoWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ base_url + jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					$('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$faviconpickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add a logo');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});



		},

		getDesignForm: function (form, text) {

			if ( designIsQuery == 1 ) return;
			designIsQuery = 1;

			$.ajax({
				url: base_url + 'cms/design/get_form',
				type: 'POST',
				dataType: 'json',
				data: {form: form},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				designIsQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					$('#design-form-panel .form-title').html(text);
					$('#design-form-panel .form-container').html(data.form);
					MSPB.OpenMediumWPageNav();

					MSPB.designLogoUpload();
					MSPB.designFaviconUpload();
				};
				console.log("complete");
			});
				

		},

		sideNavPanelSlider: function () {
			
			$('a[data-toggle="sidenavslider"]').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
			});

		},

		OpenMediumWPageNav: function () {
			$('body').addClass('page-nav-medium-w');
		},

		CloseMediumWPageNav: function () {
			$('body').removeClass('page-nav-medium-w');	
		},

		render: function (template, params) {
			
			var arr = [];
            switch(template) {
                case 'page-list-sidenav':
                if ( params.form == 1 ) {
                	var ID = ( params.id != null ) ? '<input name="id" type="hidden" value="'+ params.id +'" />' : '';
                	var form = '<form id="add-new-page-form" method="post">' +
		            				ID +
		            				'<input name="status" type="hidden" value="published" />' +
		            				'<input name="type" type="hidden" value="'+ params.type +'" />' +
		            				'<input name="parent" type="hidden" value="'+ params.parent +'" />' +
		            				'<input name="title" type="text" class="form-control form-page-list" data-trigger="focus" title="Page Title" data-toggle="tooltip" value="'+ params.name +'" />' +
		            			'</form>';
		            }else{
		            	var form = '';
		            }

		            if ( params.type == "Folder" ) {
		            	var indicator = '<div class="form-group"><a href="#folder-nav-wrap-'+ params.id + '" class="toggle-folder-nav folder-nav-indicator animated"><span class="ion-android-arrow-dropdown"></span></a></div>';

		            	var child_pages = '<div class="folder-nav-wrap-'+ params.id +'">'+
		            						'<ol id="folder-nav-'+ params.id +'" class="folder_pages_navigation active"></ol>'+
		            						'<div class="clearfix top5"></div>' +
											'<a class="btn-folder-add-page" data-type="Folder" data-parent="'+ params.id +'" href="#"><span class="ion-plus-round"></span> Add Page</a>' +
											'<div class="clearfix top10"></div>'+
		            						'</div>';		            	
		            }else{
		            	var indicator = '';
		            	var child_pages = '';
		            }

		            var icon_class = 'ion-document';

		            if ( params.type == "Page" ) {
		            	icon_class = 'ion-document';
		            }
		            if ( params.type == "Folder" ) {
		            	icon_class = 'ion-ios-folder-outline';
		            }
		            if ( params.type == "Products" ) {
		            	icon_class = 'ion-bag';
		            }

		            var parent_page = ( params.parent ==0 ) ? 'parent-page' : '';
		            var page_url = ( params.url !=null ) ? params.url : base_url + 'cms/pages';


                    arr = ['<li id="page-li-id-', params.id ,'" class="active ', parent_page ,'">',
                    			'<a data-page-id="', params.id ,'" data-type="', params.type ,'" href="', page_url ,'">',
                    				'<span class="side-page-icon icon ',icon_class,'"></span>',
                    				'<span class="page-title">', params.name ,'</span>',
                    			'</a>',
                    			'<div class="page-nav-action form-inline">',
									'<div class="form-group right5"><a href="'+ base_url + 'cms/pages?id='+ params.id + '&settings=1' + '" class="page-settings-btn" data-placement="left" data-toggle="tooltip" title="Settings"><span class="ion-gear-a"></span></a></div>',
									indicator,
                    			'</div>',
                    			child_pages,
	                   			form,
                    		'</li>'];
                break;

               
            }       
            return arr.join('');

		},


	};

	MSPB.init();

});	