var webArtJs = {
    vars:{
      base_url: $('#base_url').val(),
      month_names: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"],
      full_month_names: ["January","February","March","April","May","June","July","August","September","October","November","December"],
  },
	animate2ID: function (id2Animate, offset, callback) {
		var animSpeed=400; 
	    var easeType="easeInOutExpo"; 

      if ( typeof offset == "undefined" ) {
        offset = $(id2Animate).offset().top;
      }
      
	    if($.browser.webkit){ 
	        $("body").stop().animate({scrollTop: offset, complete: function () {
	          callback({ done: true })
	        }}, animSpeed, easeType);
	    } else {
	        $("html").stop().animate({scrollTop: offset, complete: function () {
	           callback({ done: true })
	        }}, animSpeed, easeType);
	    }
	},
	modal: function (params, callback) {
    params.modalID = ( params.hasOwnProperty("modalID") ) ? params.modalID : 'ms-modal';
		if ( $('#'+params.modalID).length > 0 ) { $('#'+params.modalID).remove(); };
		$('body').append(webArtJs.render('modal', params));
		var closeDelay = ( typeof params.closeDelay != "undefined" ) ? params.closeDelay : false;
    console.log(closeDelay)
		var $waModal = $('#'+params.modalID);
    console.log( $waModal )
		$waModal.modal({
      backdrop: params.backdrop,
      keyboard: params.keyboard,
      show: params.show,
    });

		if (closeDelay != false) {
			setInterval( function () {
				$waModal.modal('hide');
			}, closeDelay);
		};
	},
  messenger: function (params) {

    if ( $('.notification-wrap').length > 0 ) { $('.notification-wrap').remove(); };
    $('body').append(webArtJs.render('messenger', params));
    if ( params.closeAfter !=null ) {
      setTimeout(function () {
        var closeClass = (typeof params.closeClass != 'undefined') ? params.closeClass : 'fadeOut';
        var notif = $('.notification-wrap').removeClass('fadeInUp').addClass(closeClass);
        setTimeout(function () { notif.remove(); }, params.closeAfter + 1000);
      }, params.closeAfter);
    };

    $('.notif-close-btn').live('click', function (e) {
      e.preventDefault();
      var $this = $(this);
      
      var notif = $this.parents('.notification-wrap').removeClass('fadeInUp').addClass('fadeOut');
      setTimeout(function () { notif.remove(); }, 1000);
    });
  },
  dateTimeFormat: function (datetime) {
    var $dateAndTime = datetime.split(" ");
    var $date = $dateAndTime[0].split("-");
    var $time = ( $dateAndTime[1] !=null ) ? $dateAndTime[1].split(":") : '';
    var date = [];
    date['date'] = $date;
    date['time'] = $time;
    return date;
  },
  clearCKEditor: function () {
    for ( instance in CKEDITOR.instances ){
        CKEDITOR.instances[instance].updateElement();
        CKEDITOR.instances[instance].setData('');
    }
  },
  monthName: function (index) {
    return webArtJs.vars.month_names[index - 1];
  },
  kUpload: function (params, callback) {
    var dt = new Date();
    var month = dt.getMonth() + 1;
    var month = (month < 10) ? '0'+month : month;
    if ( $('#upload-form').length != 0 ) {
      $('#upload-form').kUploader({
        doDisplayList: params.doDisplayList,
        btnText: params.btnText,
        downloadBtnText: params.downloadBtnText,
        deleteBtnText: params.deleteBtnText,
        fileSymbol: params.fileSymbol,
        uploadPath: $('#base_url').val() + 'uploads/' + dt.getFullYear() + "/" + month,
        deleteURL: $('#base_url').val() + 'upload/delete_file',
        onFail: function () {
          webArtJs.modal({
              title: 'Error occurs!',
              closeDelay: false,
              modalID: 'upload-modal',
              content: 'Please check file/s that is too large or you selected file/s that not allowed by the system.',
              buttons: '<a data-dismiss="modal" href="#" class="btn btn-primary"> Dismiss </a>'
            });
        },
        success: callback,
      });
    };  
  },
  iftd: function (attach_id, attachment_for, callback) {
    if ( typeof $.files_uploaded != 'undefined' && attach_id !=null && attachment_for !=null ){
      $.ajax({
        url: $('#base_url').val() + 'upload/iftd',
        type: 'POST',
        dataType: 'json',
        data: {files: $.files_uploaded, attach_id: attach_id, attachment_for: attachment_for },
      })
      .done(function(data) {
        if ( data.is_valid ) {
          // empty uploaded files array
            $.files_uploaded.length = 0;
            console.log( $.files_uploaded.length );
           
        };
        console.log("success");
      })
      .fail(function(e) {
        console.log(e.responseText)
        console.log("error");
      })
      .always(callback);
    }
  },
  expander: function () {
    $('div.shorten-wrapper').expander({
          slicePoint: 300,
          preserveWords: false,
          // expandEffect: 'slideDown',
          // collapseEffect: 'slideUp',
          expandSpeed: 0,
          collapseSpeed: 0,
          afterExpand: function() {
            $(this).find('.details').css({display: 'inline'});
          },
          expandText: '<strong>Continue Reading →</strong>',
          userCollapseText: '',
      });
  },

  formErrors: function (errors) {
    
    if ( typeof errors == "undefined" && errors.length == 0 ) return;

    $.each( errors , function(index, val) {
      var $cur_input = $("#" + index);
      if (val !="") {
        if ( $cur_input.length == 0 ) return;
        if ( $cur_input.find('.help-block').length == 0 ){
          $cur_input.after('<span class="help-block"></span>');
        }
        $cur_input.parents('.form-group').addClass('has-error').find('.help-block').text(val);        
      }else{
        $cur_input.parents('.form-group').removeClass('has-error').find('.help-block').text("");
      }
    });

  },
  ajaxError: function (params) {
    
    var header = ( typeof params !== "undefined" && typeof params.header !== "undefined" ) ? params.header : "Oooops!";
    var message = ( typeof params !== "undefined" && typeof params.message !== "undefined" ) ? params.message : "There are some error found. Please try again later.";
    var position = ( typeof params !== "undefined" && typeof params.position !== "undefined" ) ? params.position : "top-left notif-danger";

    webArtJs.messenger({
      header: header,
      message: message,
      closeAfter: 5000,
      position: position,
    }); 

  },

	render : function(template,params){
		var arr = [];
		switch(template){
			case 'modal':
				var buttons = (params.buttons!=null) ? '<div id="ms-modal-footer" class="modal-footer">' + params.buttons + '</div>' : '';
				arr = ['<div class="modal fade" id="', params.modalID ,'" tabindex="-1" role="dialog"><div class="modal-dialog">',
	  					'<div id="ms-modal-content" class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
	      				'<h4 class="modal-title">',params.title,'</h4></div><div class="modal-body">',params.content,'</div>',buttons,'</div></div></div>'];
			break;
      case 'notif':
        arr = ['<li id="notif-id-', params.notif_id ,'"><a href="', params.url ,'"> <span class="thumb">', params.thumb ,'</span> <span class="name">', params.name ,'</span> ', 
               '<span class="msg">', params.msg ,'</span> <span class="notif-date">', params.notif_date ,'</span> </a></li>'];
      break;

      case 'messenger':
        var closeBtn = ( params.closeBtn !=null ) ? '<button type="button" class="close notif-close-btn" data-dismiss="alert" aria-label="Close"> <span aria-hidden="true">&times;</span> </button>' : '';
        var position = ( params.position !=null ) ? params.position : 'top';
        var icon = ( typeof params.icon != "undefined" ) ? '<span class="notif-icon">'+ params.icon +'</span>' : '<span class="notif-icon"><span class="ion-ios-information-outline"></span></span>';

        var with_icon = ( icon !=null ) ? 'with-icon' : '';
        arr = ['<div class="animated fadeInUp notification-wrap ', position ,' ', with_icon ,'"><div class="notification">', icon ,'<div class="notif-body">', closeBtn ,
               '<div class="notif-header">', params.header ,'</div><div class="notif-content">', params.message ,'</div>',
               '</div></div></div>'];
      break;
			
		}		
		return arr.join('');
		
	},
  beep: function () {
    $('#notification-audio')[0].play();
  },
  encodeEntities: function (s){
    return $("<div/>").text(s).html();
  },
  dencodeEntities: function (s){
    return $("<div/>").html(s).text();
  },
  getURLParameter: function (sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    } 
  },
  refreshPlugins: function () {
    $('[rel="tooltip"]').tooltip();
    $('[rel="popover"]').popover();
    if ( $('.textarea-auto-h').length > 0 ) {
      $('.textarea-auto-h').autosize();
    };

    if ( $('img.lazy').length !=0 ) {
      $("img.lazy").lazyload({
          effect : "fadeIn"
      });
      $("img.lazy").show().lazyload();
    };
  },

  hideMeShowThis: function () {
      
    $.each( $('.hide-show') , function(index, val) { 
      var $target = $($(this).data('target'));
      $target.hide();
    });

    $('.hide-show').live('click', function (e) {
      e.preventDefault();
      var $target = $($(this).data('target'));

      $(this).hide();
      $target.show();
    });      
      
  },

  

}