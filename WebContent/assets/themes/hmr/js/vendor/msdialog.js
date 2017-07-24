;(function ($) {
	
	var defaults = {
		wrapper_class: "ms-dialog-wrapper",
		is_visible: "visible",
		title: 'title',
		content: 'content',
		footer: '',
		width: 400,
		height: 290,
		position: {
			top: null,
			left: null,
			right: null,
			bottom: null,
		}
	};

	function dialog (element, options) {
		var widget = this;
		if ( element.length == 0 ) {
			console.log("Plugin can't proceed, element is not found.");
			return false;
		};
		
		widget.config = $.extend(true, {}, defaults, options);
		widget.el = element;

		widget.cycle();

		$.each(widget.config, function(key, val) {
			if ( typeof val === "function" ) {
				widget.el.on(key + ".msdialog", function (e, param) {
					return val(e, widget, param);
				});
			};
		});

		widget.init();

	};

	dialog.prototype.cycle = function () {
		this.prepare_template();
	};

	dialog.prototype.prepare_template = function () {
		
		var tpl = $('<div />', {
			'class': 'main-dialog-wrapper active-dialog ' + this.config.wrapper_class + ' ' + this.config.is_visible,
		}).css({
			'width': this.config.width,
			// 'height': this.config.height,
			'top': this.config.position.top,
			'left': this.config.position.left,
			'right': this.config.position.right,
			'bottom': this.config.position.bottom,
		}).append( 
			$('<div />',{
				'class': 'dialog-wrapper',
			}).append( $('<div />', {
				'class': 'dialog-header',
				'text': this.config.title,
			})).append( $('<div />', {
				'class': 'dialog-body',
				'width': this.config.width,
				'height': this.config.height,
			}).html(this.config.content)).append( $('<div />', {
				'class': 'dialog-footer',
				'html': this.config.footer,
			}))
		);

		this.el.html( tpl );

	};

	dialog.prototype.is_mobile_device = function () {
		return navigator.userAgent.toLowerCase().match(/(iphone|ipod|ipad|android)/);
	};

	dialog.prototype.init = function () {
		this.el.trigger('created.msdialog');
	};

	$.fn.msdialog = function (options) {
		new dialog(this.first(), options);
		return this.first();
	};

}(jQuery));
