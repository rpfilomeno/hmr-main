/*! Lazy Load 1.9.3 - MIT license - Copyright 2010-2013 Mika Tuupola */
!function(a,b,c,d){var e=a(b);a.fn.lazyload=function(f){function g(){var b=0;i.each(function(){var c=a(this);if(!j.skip_invisible||c.is(":visible"))if(a.abovethetop(this,j)||a.leftofbegin(this,j));else if(a.belowthefold(this,j)||a.rightoffold(this,j)){if(++b>j.failure_limit)return!1}else c.trigger("appear"),b=0})}var h,i=this,j={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:b,data_attribute:"original",skip_invisible:!0,appear:null,load:null,placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return f&&(d!==f.failurelimit&&(f.failure_limit=f.failurelimit,delete f.failurelimit),d!==f.effectspeed&&(f.effect_speed=f.effectspeed,delete f.effectspeed),a.extend(j,f)),h=j.container===d||j.container===b?e:a(j.container),0===j.event.indexOf("scroll")&&h.bind(j.event,function(){return g()}),this.each(function(){var b=this,c=a(b);b.loaded=!1,(c.attr("src")===d||c.attr("src")===!1)&&c.is("img")&&c.attr("src",j.placeholder),c.one("appear",function(){if(!this.loaded){if(j.appear){var d=i.length;j.appear.call(b,d,j)}a("<img />").bind("load",function(){var d=c.attr("data-"+j.data_attribute);c.hide(),c.is("img")?c.attr("src",d):c.css("background-image","url('"+d+"')"),c[j.effect](j.effect_speed),b.loaded=!0;var e=a.grep(i,function(a){return!a.loaded});if(i=a(e),j.load){var f=i.length;j.load.call(b,f,j)}}).attr("src",c.attr("data-"+j.data_attribute))}}),0!==j.event.indexOf("scroll")&&c.bind(j.event,function(){b.loaded||c.trigger("appear")})}),e.bind("resize",function(){g()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&e.bind("pageshow",function(b){b.originalEvent&&b.originalEvent.persisted&&i.each(function(){a(this).trigger("appear")})}),a(c).ready(function(){g()}),this},a.belowthefold=function(c,f){var g;return g=f.container===d||f.container===b?(b.innerHeight?b.innerHeight:e.height())+e.scrollTop():a(f.container).offset().top+a(f.container).height(),g<=a(c).offset().top-f.threshold},a.rightoffold=function(c,f){var g;return g=f.container===d||f.container===b?e.width()+e.scrollLeft():a(f.container).offset().left+a(f.container).width(),g<=a(c).offset().left-f.threshold},a.abovethetop=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollTop():a(f.container).offset().top,g>=a(c).offset().top+f.threshold+a(c).height()},a.leftofbegin=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollLeft():a(f.container).offset().left,g>=a(c).offset().left+f.threshold+a(c).width()},a.inviewport=function(b,c){return!(a.rightoffold(b,c)||a.leftofbegin(b,c)||a.belowthefold(b,c)||a.abovethetop(b,c))},a.extend(a.expr[":"],{"below-the-fold":function(b){return a.belowthefold(b,{threshold:0})},"above-the-top":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-screen":function(b){return a.rightoffold(b,{threshold:0})},"left-of-screen":function(b){return!a.rightoffold(b,{threshold:0})},"in-viewport":function(b){return a.inviewport(b,{threshold:0})},"above-the-fold":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-fold":function(b){return a.rightoffold(b,{threshold:0})},"left-of-fold":function(b){return!a.rightoffold(b,{threshold:0})}})}(jQuery,window,document);

var webArtJs = {
    vars:{
      base_url: $('#base_url').val(),
      month_names: ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"],
      full_month_names: ["January","February","March","April","May","June","July","August","September","October","November","December"],
  },
	animate2ID: function (id2Animate, callback) {
		var animSpeed=400; 
	    var easeType="easeInOutExpo"; 
	    if($.browser.webkit){ 
	        $("body").stop().animate({scrollTop: $(id2Animate).offset().top, complete: function () {
	          callback({ done: true })
	        }}, animSpeed, easeType);
	    } else {
	        $("html").stop().animate({scrollTop: $(id2Animate).offset().top, complete: function () {
	           callback({ done: true })
	        }}, animSpeed, easeType);
	    }
	},
	modal: function (params, callback) {
    params.modalID = ( params.hasOwnProperty("modalID") ) ? params.modalID : 'ims-modal';
		if ( $('#'+params.modalID).length > 0 ) { $('#'+params.modalID).remove(); };
		$('body').append(webArtJs.render('modal', params));
		var closeDelay = (params.closeDelay!=null) ? params.closeDelay : 3000;
		var $waModal = $('#'+params.modalID);
    console.log( $waModal )
		$waModal.modal({
      backdrop: params.backdrop,
      keyboard: params.keyboard,
      show: params.show,
    });

		if (params.closeDelay != false) {
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
        console.log(closeClass)
        var notif = $('.notification-wrap').addClass(closeClass);
        setTimeout(function () { notif.remove(); }, 5000);
      }, params.closeAfter);
    };
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
	render : function(template,params){
		var arr = [];
		switch(template){
			case 'modal':
				var buttons = (params.buttons!=null) ? '<div id="ims-modal-footer" class="modal-footer">' + params.buttons + '</div>' : '';
				arr = ['<div class="modal fade" id="', params.modalID ,'" tabindex="-1" role="dialog"><div class="modal-dialog">',
	  					'<div id="ims-modal-content" class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
	      				'<h4 class="modal-title">',params.title,'</h4></div><div class="modal-body">',params.content,'</div>',buttons,'</div></div></div>'];
			break;
      case 'notif':
        arr = ['<li id="notif-id-', params.notif_id ,'"><a href="', params.url ,'"> <span class="thumb">', params.thumb ,'</span> <span class="name">', params.name ,'</span> ', 
               '<span class="msg">', params.msg ,'</span> <span class="notif-date">', params.notif_date ,'</span> </a></li>'];
      break;

      case 'messenger':
        var closeBtn = ( params.closeBtn !=null ) ? '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>' : '';
        var position = ( params.position !=null ) ? params.position : 'top';
        arr = ['<div class="animated fadeInUp notification-wrap ', position ,'"><div class="notification"><div class="notif-body">', closeBtn ,
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

  

}