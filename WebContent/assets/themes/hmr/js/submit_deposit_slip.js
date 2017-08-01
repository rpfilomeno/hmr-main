$(document).ready(function() {
		
	var base_url = $('#base_url').val()
	,	is_query = 0
	,	$depositSlipModal = $('#deposit-slip-modal')
	,	depositSlip = {
		init: function() {
			this.careerAttachments();
			this.depositSlipForm();
		},

		depositSlipForm: function() {
			
			$('#deposit-slip-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				if ( is_query == 1 ) return;
				is_query = 1;

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
					is_query = 0;

					console.log(data)

					if ( typeof data !="undefined" && data.is_valid ) {

						$depositSlipModal.modal('show');
						$depositSlipModal.find('.modal-title').html('Thank you!');
						$depositSlipModal.find('.modal-body').html('<p>Deposit slip has been submitted. You will receive a confirmation email once validated.</p>');
						$('#bank_name').val('')
						$('#amount').val('')
						$('#file_path').val('')
						$('.has-error').find('.help-block').text('');
						$('.has-error').removeClass('has-error');
						$('#current-file-wrap').html('');

					}

					if ( typeof data !="undefined" && !data.is_valid ) {

						$depositSlipModal.modal('show');
						$depositSlipModal.find('.modal-title').html('Error occurs');
						$depositSlipModal.find('.modal-body').html('<p>Please check all the required fields.</p>');
						

						$this.find('.form-group').removeClass('has-error');
	                    $this.find('.error-help-block').text('');
	                    $.each(data.errors, function(index, val) {
	                        if (val !="") {
	                            $("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
	                        }
	                    });
					}

					console.log("complete");
				});
				


			});

		},

		careerAttachments: function() {
			
			if ( $('#file-pickfiles-container').hasClass('uploader-init') ) return;

			var	$dropElement = $('#file-image-wrapper')
			,	max_file_count = 1;

			var $currentLogoWrap = $('#current-file-wrap');
			var $preloader = $('#file-preloader');
			var $filepickfile = $('#filepickfile');

			fileUploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'filepickfile', // you can pass an id...
				drop_element: 'file-image-wrapper',
				dragdrop: false,
				container: document.getElementById('file-pickfiles-container'), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size: '50mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,jpeg,gif,png"},
					]
				},
				multi_selection: false,
				multipart_params : {
			        "attach_id" : $('#sessin_user_id').val(),
			        "attachment_for" : "deposit_slip",
			    }
			});
			// console.log(fileUploader);

			fileUploader.init();

			$('#file-pickfiles-container').addClass('uploader-init');

			// UploadProgress
			fileUploader.bind('UploadProgress', function (up, file) {
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
			fileUploader.bind('FilesAdded', function (up, files) {
			    // console.log(files)
				
				// if(up.files.length > max_file_count){
			 //        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
			 //        for(var i in files){
				// 		fileUploader.removeFile(files[i]);
				// 	};
			 //        return;
			 //    }

				for(var i in files){
					var file = files[i];
					$preloader.hide().html('<div id="'+ file.id +'"><div class="media-preview file-detail files-added">'+ '<div class="progress progress-sm"><div class="progress-bar progress-bar-success progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></div>');
				};
				fileUploader.start();
				fileUploader.refresh();


				$('.msis-error-message-wrapper').hide();
				$currentLogoWrap.html( files[0].name );
				$filepickfile.html('<span class="ion-edit right5"></span> Change file');
			});

			// Error
			fileUploader.bind('Error', function (up, err) {
				// console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$preloader.addClass('error-file').html('<div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div>');
				}else{
					$preloader.html('<div id="'+ err.file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div></div>');
				}
				fileUploader.refresh();
			});



			fileUploader.bind('FileUploaded', function (up, file, response) {
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)

				if ( jsonResponse.is_valid ) {

					$('#file').parents('.form-group').removeClass('has-error').find('.help-block').text('');


					$('.msis-error-message-wrapper').hide();
					$filepickfile.html('<span class="ion-edit right5"></span> Change file');

					$('#file_path').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					// $currentLogoWrap.html( jsonResponse.upload_data.file_name );

					$currentLogoWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ base_url + jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name  +'">' );
					
					fileUploader.removeFile(file.id);

				};

				if ( !jsonResponse.is_valid ) {
					$filepickfile.html('<span class="ion-plus-round right5"></span> Add a file');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					fileUploader.refresh();
				};

			});

		},
	};

	depositSlip.init();


});