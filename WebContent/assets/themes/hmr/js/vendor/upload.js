jQuery(document).ready(function($) {
var pdata = {
  base_url: $('#base_url').val(),
  uploadBtn: $('.upload-btn'),
  uploadImg: $('#upload-img')

}, upload = {
  init: function () {
    this.doUpload();
  },
  doUpload: function () {
    pdata.uploadBtn.live('click', function () {
      $('#create_cropped_type').val($(this).data('type'))
      $('#upload_type').val($(this).data('type'))
      pdata.uploadImg.click();
    }); 
    pdata.uploadImg.live('change', function(){
      $('#upload-form').ajaxForm({
          // target:     '#avatarCrop', 
          // url:        'comment.php', 
          dataType:  'json', 
          beforeSend: function() {
              // status.empty();
              pdata.uploadBtn.prop('disabled', true);
              $('#prog-wrap').show();
              var percentVal = '0%';
              $('.progress').removeClass('active');
              $('.progress .bar').width(percentVal);
              $('.progress .bar').html(percentVal);
          },
          uploadProgress: function(event, position, total, percentComplete) {
              var percentVal = percentComplete + '%';
              $('#prog-wrap').show();
              $('.progress').addClass('active');
              $('.progress .bar').width(percentVal);
              $('.progress .bar').html(percentVal);
              console.log(percentComplete)
          },
          success: function(e) {
            if (e.is_valid) {
              $('#filename').val(e.filename);
              var uploadedPic = $('<img />',{
                src: pdata.base_url + 'img/uploads/' + e.filename,
                id: "cover-photo"
              });
              $('#uploaded-img').html(uploadedPic);
              pdata.uploadBtn.text('Change Photo');
              $('#link-wrap').removeClass('hide');
              $('#file-link').val( '![]('+ pdata.base_url + 'img/uploads/' + e.filename +')' ).focus().select();
              // $('#upload-modal').removeClass('open');
              // $("#entry-markdown").insertAtCaret( '![Alt text]('+ pdata.base_url + 'img/uploads/' + e.filename +')' );
              // $("#entry-markdown").trigger("change");
              $("#file-link").on({
                  copy : function(){
                      var msg = Messenger().post({
                        message: "Image link has been coppied! Close this modal.",
                        showCloseButton: true,
                        actions: {
                          close: {
                            label: 'Close',
                            action: function () {
                              msg.hide();
                              $('#upload-modal').removeClass('open');
                            }
                          },
                        }
                      }) 
                  }
              });
            }else{
              jonJs.modal({
                title: 'Error occurs!',
                message: 'Please choose other picture and try again.'
              });
              $('#link-wrap').addClass('hide');
            }
            
            pdata.uploadBtn.prop('disabled', false);
            $('#pic-filename-error').html('');
            var percentVal = '100%';
            $('.progress').addClass('active');
            $('.progress .bar').width(percentVal);
            $('.progress .bar').html(percentVal);
            $('#prog-wrap').hide();
            console.log(e)
          },
          error: function (e) {
              // $('#ava-modal-head').text("Upload Error")
              // $('#cropping-area').html(e.responseText);
              // $('#avatar-crop-btn').hide();
              // $('#avatarCrop').modal('show');
              // $('#prog-wrap').fadeOut();
              pdata.uploadBtn.prop('disabled', false);
              console.log(e.responseText);
              console.log('error');
              $('#prog-wrap').hide();

              jonJs.modal({
                title: 'Error occurs!',
                message: 'Please choose other picture and try again.'
              });
              console.log( e.responseText )
          }
      }).submit();
    });
  },
}


upload.init();


});