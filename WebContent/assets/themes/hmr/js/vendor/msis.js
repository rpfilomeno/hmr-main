;(function ($) {
	
	var defaults = {
		load_more_class: "btn ms-button-trigger",
		load_more_text: "Load More",
		trigger_type: "scroll",
		ajaxOptions: {
			url: "", //required
			type: "POST",
			dataType: "json",
		},
		response: {
			type: "html",
			results: "results",
			offset: "start",
			limit: "limit",
			total_rows: "total_rows",
		},
		wrapper_class: "msis-wrapper",
		errorMessage: "There are no data to display.",
		errorClass: 'msis-error-message',
		is_query: false,
    	loader_image: "../img/loader.gif",
    	loader_image_class: "ms-loader-image",
		loader: null,
		FOOTER_POSITION_THRESHOLD : 60,
    	MOBILE_FOOTER_POSITION_THRESHOLD : 600,
	};

	function infiniteScroll (element, options) {
		var widget = this;
		if ( element.length == 0 ) {
			console.log("Plugin can't proceed, element is not found.");
			return false;
		};
		if ( element.data('url') ) {
			defaults.ajaxOptions.url = element.data('url');
		};

		widget.config = $.extend(true, {}, defaults, options);
		widget.el = element;

		widget.cycle();

		$.each(widget.config, function(key, val) {
			if ( typeof val === "function" ) {
				widget.el.on(key + ".msis", function (e, param) {
					return val(e, widget, param);
				});
			};
		});

		widget.init();

	};

	infiniteScroll.prototype.cycle = function () {
		this.wrap();
		this.prepare_loader();
		this.fetch();
		if ( this.config.trigger_type == "scroll" ) {
		    this.fetch_by_scroll( this.fetch.bind(this) );
		} else if ( this.config.trigger_type == "button" ) {
			this.fetch_by_button( this.fetch.bind(this) );
		}else{

		}
	};

	infiniteScroll.prototype.fetch = function () {
		var widget = this,
		response = widget.config.response;

		widget.el.trigger('fetch.msis');

		if ( widget.config.is_query ) return;
		widget.config.is_query = true;
		widget.show_loader();

		if ( widget.config.trigger_type == "button" ) {
			widget.hide_load_button();
		};

		// increment offset by per_page 
		var start = widget.get_start();
		var dataObj = {
			data: {
				start: start,
			},
		},
		ajaxSettings = $.extend(true, {}, widget.config.ajaxOptions, dataObj);
		
		// console.log(ajaxSettings);
		$.ajax(ajaxSettings)
		.done(function(data) {
			// widget.config.done(widget.el, data);
			// console.log(data)
			widget.el.trigger('done.msis', data);

			if ( data != null && data.length != 0 && data[ response.total_rows ] !=0 ) {
				widget.el.data('total-rows', data[ response.total_rows ]);
				widget.el.data('limit', data[ response.limit ]);
				widget.el.data('start', data[ response.limit ] + start);
				widget.el.data('remaining', Math.max(0, data[ response.total_rows ] - widget.el.data('start')) );

				if ( response.type == "html" ) {
					widget.el.append(data[ response.results ]);
				};
				widget.hide_errors();
			}else{
				widget.display_errors();

				if ( widget.config.trigger_type == "button" ) {
					widget.hide_load_button();
				};
			}
			widget.el.trigger('done_append.msis', data);
			// console.log("success");
		})
		.fail(function(data) {
			console.log("error");
			// console.log(data.responseText);

			var returnVal = widget.el.trigger("responseError.msis", data);

			if ( returnVal !== false ) {
				widget.display_errors();
			};
		})
		.always(function() {
			if ( widget.el.data('remaining') !=0 ) {
				widget.config.is_query = false;

				if ( widget.config.trigger_type == "button" ) {
					widget.show_load_button();
				};
			}else{
				if ( widget.config.trigger_type == "button" ) {
					widget.hide_load_button();
				};
				// widget.display_errors("Nothing follows");
			}
			widget.hide_loader();

			// console.log("complete");
		});
	};

	infiniteScroll.prototype.get_start = function () {
		return this.el.data('start') ? this.el.data('start') : 0; 
	};

	infiniteScroll.prototype.get_total_rows = function () {
		return this.el.data('total-rows') ? this.el.data('total-rows') : 0; 
	};

	infiniteScroll.prototype.fetch_by_scroll = function (callback) {
		$(window).scroll(function() {
            if  ($(window).scrollTop() + $(window).height() >
                this.get_doc_height() - this.get_footer_threshold() ) {
                callback();
            }

        }.bind(this));	
	};

	infiniteScroll.prototype.fetch_by_button = function (callback) {
		this.prepare_button();

		$(this.button_trigger[0]).live('click', function (e) {
			e.preventDefault();
			$(this).blur();
			callback();
		});

	};

	infiniteScroll.prototype.prepare_button = function () {
		
		this.button_trigger = $("<button>", {
            "class": this.config.load_more_class,
            "text": this.config.load_more_text,
        });

		this.button_trigger_container = $("<div>").css({
            "text-align": "center",
            "padding": "10px",
            "clear": "both",
            "display": "none",
            "class": 'ms-button-trigger-container',
        }).append(this.button_trigger);

        $(this.el.parents('.' + this.config.wrapper_class ).append(this.button_trigger_container));

	}

	infiniteScroll.prototype.wrap = function () {
		
		this.wrapper = $('<div>', {
			"class": this.config.wrapper_class,
		});

		this.el.wrap( this.wrapper );

	};

	infiniteScroll.prototype.prepare_loader = function () {
		
		this.loader = $("<div>").css({
            "display": "none",
            "text-align": "center",
            "padding": "10px",
            "margin-bottom": "10px",
            "clear": "both",
        }).addClass('ms-loader-image-container').append($("<img>", {
            "src": this.config.loader_image,
            "class": this.config.loader_image_class,
        }));

        this.el.after(this.loader);

	};

	infiniteScroll.prototype.show_loader = function () {
		this.loader.show();
	};

	infiniteScroll.prototype.hide_loader = function () {
		this.loader.hide();
	};

	infiniteScroll.prototype.show_load_button = function () {
		$(this.button_trigger_container).show();
	};

	infiniteScroll.prototype.hide_load_button = function () {
		$(this.button_trigger_container).hide();
	};

	infiniteScroll.prototype.display_errors = function ( message ) {

		var err_message = (message == null) ? this.config.errorMessage : message;
		this.error_message = $("<div>").css({
            "clear": "both",
        }).addClass('msis-error-message-wrapper').append($('<p/>', {
			html: err_message,
			"class": this.config.errorClass,
		}));

        this.el.after(this.error_message);

        this.config.is_query = true;
        this.el.data('remaining', 0);

	};

	infiniteScroll.prototype.hide_errors = function () {
		this.el.parents( '.' + this.config.wrapper_class ).find('.msis-error-message-wrapper').hide();
	};

	infiniteScroll.prototype.get_doc_height = function () {
		var D = document;

        return Math.max(
            Math.max(D.body.scrollHeight, D.documentElement.scrollHeight),
            Math.max(D.body.offsetHeight, D.documentElement.offsetHeight),
            Math.max(D.body.clientHeight, D.documentElement.clientHeight)
        );	
	};

	infiniteScroll.prototype.get_footer_threshold = function () {
		return this.is_mobile_device() ?
            this.config.MOBILE_FOOTER_POSITION_THRESHOLD : this.config.FOOTER_POSITION_THRESHOLD;
	};

	infiniteScroll.prototype.is_mobile_device = function () {
		return navigator.userAgent.toLowerCase().match(/(iphone|ipod|ipad|android)/);
	};

	infiniteScroll.prototype.init = function () {
		// this.config.question
		// this.config.created(this.el);
		this.el.trigger('created.msis');
	};

	

	$.fn.msis = function (options) {
		new infiniteScroll(this.first(), options);
		return this.first();
	};

}(jQuery));


// github infiniteScroll js

// https://github.com/husseycoding/infinitescroll