;(function ($) {
	
	var defaults = {
		url: '',
		title: '',
		text: '',
		image: '',
		network: '',
	};

	var templates = {
        facebook: 'https://www.facebook.com/sharer/sharer.php?s=100&p[title]={title}&p[summary]={text}&p[url]={url}&p[images][0]={image}',
        twitter: 'https://twitter.com/intent/tweet?url={url}&text={text}',
        pinterest: 'https://www.pinterest.com/pin/create/button/?media={image}&url={url}&description={text}',
        linkedin: 'https://www.linkedin.com/shareArticle?mini=true&url={url}&title={title}&summary={text}&source={url}',
        tumblr: 'https://tumblr.com/share?s=&v=3&t={title}&u={url}',
        blogger: 'https://blogger.com/blog-this.g?t={text}&n={title}&u={url}',
        delicious: 'https://delicious.com/save?url={url}&title={title}',
        googleplus: 'https://plus.google.com/share?url={url}',
        digg: 'https://digg.com/submit?url={url}&title={title}',
        reddit: 'http://reddit.com/submit?url={url}&title={title}',
    }

    // https://www.linkedin.com/shareArticle?mini=true&url=rtl.ph&title=test&summary=&source=

	function msSocialShare (element, options) {
		var widget = this;

		if ( $(element).length == 0 ) {
			console.log("Plugin can't proceed, element is not found.");
			return false;
		};
		
		widget.config = $.extend(true, {}, defaults, options);
		widget.el = $(element);

		widget.cycle( widget.config );

		$.each(widget.config, function(key, val) {
			if ( typeof val === "function" ) {
				widget.el.on(key + ".mssocial", function (e, param) {
					return val(e, widget, param);
				});
			};
		});

		widget.init();

	};

	msSocialShare.prototype.cycle = function (options) {
		var widget = this;

		// fetch if count available
		if ( widget.el.data('fetch-count') ) {
			this.getCount();	
		}
		
		// on btn click
		if ( typeof widget.el.data('ms-social-network') !="undefined" && $.inArray(widget.el.data('ms-social-network'), templates) ) {
			this.btnOnClick(options);	
		}
		
	};

	msSocialShare.prototype.btnOnClick = function (options) {
		var widget = this;

		widget.el.on('click', function(e) {
			e.preventDefault();
			var final_url = widget.getNetworkLink( widget.el.data('ms-social-network') );
			// console.log(final_url)
			var screen_width = screen.width;
            var screen_height = screen.height;
            var popup_width = options.width ? options.width : (screen_width - (screen_width*0.3));
            var popup_height = options.height ? options.height : (screen_height - (screen_height*0.3));
            var left = (screen_width/2)-(popup_width/2);
            var top = (screen_height/2)-(popup_height/2);
            var parameters = 'toolbar=0,status=0,width=' + popup_width + ',height=' + popup_height + ',top=' + top + ',left=' + left;
            return window.open(final_url, '', parameters) && false;

		});

	};

	msSocialShare.prototype.getNetworkLink = function (network) {
		var widget = this;
		var url = templates[network];

        url = url.replace('{url}', encodeURIComponent( widget.el.data('ms-social-url')));
        url = url.replace('{title}', encodeURIComponent( widget.el.data('ms-social-title')));
        url = url.replace('{text}', encodeURIComponent( widget.el.data('ms-social-text')));
        url = url.replace('{image}', encodeURIComponent( widget.el.data('ms-social-image')));

        if ( network == 'linkedin' ) {
        	url = url.replace('{url}', widget.el.data('ms-social-url') );
        	url = url.replace('{title}', widget.el.data('ms-social-title') );
	        url = url.replace('{text}', widget.el.data('ms-social-text') );
	        url = url.replace('{image}', widget.el.data('ms-social-image') );
        }
        return url;

	};

	msSocialShare.prototype.getCount = function () {
		var widget = this;

		switch ( this.el.data('ms-social-network') ) {
			case 'facebook':
				widget.fetchFacebook()
				break;
			case 'twitter':
				// widget.fetchTwitter()
				break;
			case 'pinterest':
				widget.fetchPinterest()
				break;
			case 'googleplus':
				widget.fetchGooglePlus()
				break;
			case 'linkedin':
				widget.fetchLinkedIn()
				break;
			default:
				break;
		}
	};

	msSocialShare.prototype.fetchFacebook = function () {
		var widget = this;

		$.getJSON('https://graph.facebook.com/?id=' + widget.el.data('ms-social-url') + '&callback=?')
		.done(function(data) {
			if (data.shares) {
				widget.el.find('.share-count').text(data.shares).addClass('is-loaded');
			} else {
				widget.el.find('.share-count').remove();
			}
		}).fail(function(data) {
			widget.el.find('.share-count').remove();
		});		

	};

	msSocialShare.prototype.fetchTwitter = function () {
		var widget = this;

		$.getJSON('http://urls.api.twitter.com/1/urls/count.json?url=' + widget.el.data('ms-social-url') + '&callback=?')
	    .done(function(data) {
	        console.log(data)
	        if (data.count > 0) {
	          $twitLink.find('.share-count').text(data.count).addClass('is-loaded');
	        } else {
	          $twitLink.find('.share-count').remove();
	        }
	    })
	    .fail(function(data) {
	        $twitLink.find('.share-count').remove();
	    });

	};

	msSocialShare.prototype.fetchPinterest = function () {
		var widget = this;

		$.getJSON('https://api.pinterest.com/v1/urls/count.json?url=' + widget.el.data('ms-social-url') + '&callback=?')
		.done(function(data) {
			if (data.count  > 0) {
				widget.el.find('.share-count').text(data.count).addClass('is-loaded');
			} else {
				widget.el.find('.share-count').remove();
			}
		}).fail(function(data) {
			widget.el.find('.share-count').remove();
		});	
	};

	msSocialShare.prototype.fetchGooglePlus = function () {
		
		var widget = this;
		var currentURL = widget.el.data('ms-social-url');

		var data = {
		  method: "pos.plusones.get",
		  id: currentURL,
		  params: {
		    nolog: !0,
		    id: currentURL,
		    source: "widget",
		    userId: "@viewer",
		    groupId: "@self"
		  },
		  jsonrpc: "2.0",
		  key: "p",
		  apiVersion: "v1"
		};

		$.ajax({
		  type: "POST",
		  url: "https://clients6.google.com/rpc",
		  processData: !0,
		  contentType: "application/json",
		  data: JSON.stringify(data),
		  success: function(a) {
		    console.log(a)
		    if ( a ) {
		    	widget.el.find('.share-count').text( a.result.metadata.globalCounts.count ).addClass('is-loaded');	
		    }else{
		    	widget.el.find('.share-count').remove();
		    }
		  }
		}).fail(function(data) {
			widget.el.find('.share-count').remove();
		});;

	};

	msSocialShare.prototype.fetchLinkedIn = function () {
		var widget = this;

		$.getJSON('https://www.linkedin.com/countserv/count/share?url=' + widget.el.data('ms-social-url') + '&callback=?')
		.done(function(data) {
			if (data.count  > 0) {
				widget.el.find('.share-count').text(data.count).addClass('is-loaded');
			} else {
				widget.el.find('.share-count').remove();
			}
		}).fail(function(data) {
			widget.el.find('.share-count').remove();
		});	
	};

	msSocialShare.prototype.mailTo = function () {
		var widget = this;
	
	};


	msSocialShare.prototype.init = function () {
		this.el.trigger('created.mssocial');
	};

	

	$.fn.MSSocial = function (options) {
		return this.each(function () {
            if (!$.data(this, 'plugin_' + msSocialShare)) {
                $.data(this, 'plugin_' + msSocialShare, 
                new msSocialShare( this, options ));
            }
        });
	};

}(jQuery));

