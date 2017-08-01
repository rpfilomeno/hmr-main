// Utility
if (typeof Object.create !== 'function') {
    Object.create = function(obj) {
        function F() {};
        F.prototype = obj;
        return new F();
    }
};

(function($, window, document, undefined) {
    var kTypeahead = {
        init: function(options, elem) {
            var self = this;

            self.elem = elem;
            self.$elem = $(elem);

            self.url = (typeof options === 'string') ? options : options.url;
            self.options = $.extend({}, $.fn.kTypeAhead.options, options);

            self.renderBaseTpl();
            self.bindEvents();
            self.onTyping();
            self.closeOnEscape();
            self.onBlur();

            // console.log(self.options)
        },

        bindEvents: function() {
            var self = this;
            var curIndex = 0;
            var $kkSuggestion = ''

            self.$elem.typing({
                start: function(event, $elem) {
                    if (typeof self.options.onStartTyping === 'function') {
                        self.options.onStartTyping.apply(self.elem, arguments);
                    };
                    curIndex = 0;
                    $('.kk-suggestion').removeClass('kk-is-under-cursor');
                    if ( self.options.curTextHint != $elem.val() ) {
                    	self.$elem.removeClass('searched');
                    };
                    self.suggestionClose()
                    // console.log("starting....")
                },
                stop: function(event, $elem) {
                    self.suggestionClose()
                    self.$elem.removeClass('searched');
                    if ( self.options.doSuggestionOnStop ) {
                        self.doQuerySuggestions();
                    };

                },
                delay: self.options.delay
            });

           
            $(document).keydown(function(e) {
                // self.options.curTextHint = self.$elem.val();

                if (!self.$elem.is(":focus")) return;
                if (e.keyCode == 38) { //up
                    if ( !self.options.keyUpAndDownSelect ) return;
                    self.suggestionOpen();

                    if (curIndex != 0) { curIndex--; };
                    
                    console.log(curIndex)
                    $kkSuggestion = self.options.container.find('.kk-suggestion').removeClass('kk-is-under-cursor')
                        .eq(curIndex).addClass('kk-is-under-cursor');
                    self.$elem.val($kkSuggestion.find('.value').text());
                    self.setData();
                    // e.preventDefault();
                    return false;
                }
                if (e.keyCode == 40) {
                    if ( !self.options.keyUpAndDownSelect ) return;
                    self.suggestionOpen();

                    if ( self.options.selectFirstSuggestion ){
                        if (self.options.totalSuggestions >= curIndex) { 
                            curIndex++;
                        };
                    }

                    console.log(curIndex)
                    $kkSuggestion = self.options.container.find('.kk-suggestion').removeClass('kk-is-under-cursor')
                        .eq(curIndex).addClass('kk-is-under-cursor');
                    self.$elem.val($kkSuggestion.find('.value').text());
                    self.setData();

                    if ( !self.options.selectFirstSuggestion ){
                        if (self.options.totalSuggestions >= curIndex) { 
                            curIndex++;
                        };
                    }
                    

                    // e.preventDefault();
                    return false;
                };

                if (e.keyCode == 13) { //on enter
                    self.doEnter();
                    // e.preventDefault();
                };
            });
            
            $(self.options.searchBtn).live('click', function (e) {
                self.doEnter();
                e.preventDefault();
            });

            self.suggestionClick();
            
        },

        onBlur: function () {
            var self = this;
            if ( self.options.closeSuggestionOnBlur ) {
                self.$elem.live('blur', function () {
                    setTimeout(function () {
                        self.suggestionClose();
                    }, 200);
                });
            };
            if (typeof self.options.onBlur === 'function') {
                self.options.onBlur.apply(self.elem, arguments);
            };
        },

        doQuerySuggestions: function () {
            var self = this;
            self.options.data.query = self.$elem.val();                    
            self.suggestionOpen();
            if ( $.trim(self.$elem.val()).length != 0 && !self.$elem.hasClass('searched') ) {
                if ( self.options.curTextHint != self.$elem.val() ) {
                    self.cycle();
                    self.options.curTextHint = self.$elem.val();
                    self.$elem.addClass('searched');

                    if (typeof self.options.onDoQuerySuggestions === 'function') {
                        self.options.onDoQuerySuggestions.apply(self.elem, arguments);
                    };
                    // console.log("doQuerySuggestions ")
                };
            }else{
                self.suggestionClose();
            }
        },

        doEnter: function () {
            // console.log("doEnter")
            var self = this;
            // self.options.container.find('.kk-suggestion').removeClass('kk-is-under-cursor');
            // self.$elem.removeClass('searched');
            self.doQuerySuggestions();

            // self.$elem.val(self.options.curTextHint);
            if ( self.options.container.find('.kk-suggestion.kk-is-under-cursor').length !=0 ) {
                self.$elem.addClass('searched');
                // self.options.container.find('.kk-suggestion.kk-is-under-cursor').trigger('click');
                self.onEnterWithSelectedSuggestion();
            };
            if (typeof self.options.onEnter === 'function') {
                self.options.onEnter.apply(self.elem, arguments);
            };
        },

        onTyping: function () {
            var self = this;
            self.$elem.live('keydown', function () {
                self.options.curTextType = self.$elem.val();
                if ( self.options.curTextHint != self.$elem.val() ) {
                    self.$elem.removeClass('searched');
                }else{
                    self.$elem.addClass('searched');
                }
                if (typeof self.options.onTyping === 'function') {
                    self.options.onTyping.apply(self.elem, arguments);
                };
            });
        },
        setData: function () {
            var self = this;
            var kkUnderCursor = self.options.container.find('.kk-suggestion.kk-is-under-cursor');

            if ( kkUnderCursor.length != 0 ) {
                self.$elem.data('value', self.options.curTextHint);
                // .data('region-id', kkUnderCursor.data('region-id'))
                // .data('province-id', kkUnderCursor.data('province-id'))
                // .data('barangay-id', kkUnderCursor.data('barangay-id'))
                // .data('city-muni-id', kkUnderCursor.data('city-muni-id'))
                // .data('long', kkUnderCursor.data('long'))
                // .data('lat', kkUnderCursor.data('lat'))
                // .data('zoom', kkUnderCursor.data('zoom'))
                // .data('type', kkUnderCursor.data('type'));

                if (typeof self.options.onSetData === 'function') {
                    self.options.onSetData.apply(self.elem, arguments);
                };
            };
        },

        onEnterWithSelectedSuggestion: function () {
            var self = this;
            var kkUnderCursor = self.options.container.find('.kk-suggestion.kk-is-under-cursor');
                if ( kkUnderCursor.length == 0 ) return;
                self.options.curTextHint = kkUnderCursor.find('.value').text();
                self.$elem.addClass('searched').val(kkUnderCursor.find('.value').text());
                self.setData();
            if (typeof self.options.onEnterWithSelectedSuggestion === 'function') {
                self.options.onEnterWithSelectedSuggestion.apply(self.elem, arguments);
            };
        },

        suggestionClose: function () {
            var self = this;
            self.options.container.find('.kk-dropdown-menu').removeClass('open').addClass('closed');
            self.options.container.find('.kk-message').remove();
            if (typeof self.options.onSuggestionClose === 'function') {
                self.options.onSuggestionClose.apply(self.elem, arguments);
            };
        },
        
        suggestionOpen: function () {
            var self = this;
            self.options.container.find('.kk-dropdown-menu').removeClass('closed').addClass('open');
            self.options.container.find('.kk-dataset-query').scrollTop(0);
            if (typeof self.options.onSuggestionOpen === 'function') {
                self.options.onSuggestionOpen.apply(self.elem, arguments);
            };
        },

        suggestionClick: function () {
        	var self = this;

            self.$elem.parents('.kk-suggest-wrap').addClass('test_class');

            $('.kk-suggestion').live('click', function (e) {
            	var $this = $(this);
	            self.options.curTextHint = $this.find('.value').text();
                $this.parents('.kk-suggest-wrap').find('input, textarea').val($this.find('.value').text());
                $this.addClass('kk-is-under-cursor');
                $('.kk-suggestion').removeClass('kk-is-under-cursor');
                $this.addClass('kk-is-under-cursor');

                self.setData();

                // console.log("kk-suggestion clicccccccccccck")
				if (typeof self.options.onSuggestionClick === 'function') {
	                self.options.onSuggestionClick.apply(self.elem, arguments);
	            };
            });

        },

        cycle: function() {
            var self = this;
            if (typeof self.options.onFetch === 'function') {
                self.options.onFetch.apply(self.elem, arguments);
            };
            self.fetch().done(function(results) {
                if (typeof self.options.onComplete === 'function') {
                    self.options.onComplete.apply(self.elem, arguments);
                };

                if (typeof results == 'undefined') return;
                if ( !self.options.customDisplay ) {
                    self.display(results);
                };
                if ( results!=null && typeof results.results != 'undefined' && results.results != null ) {

                    self.options.totalSuggestions = self.options.container.find('span.kk-suggestions').find('.kk-suggestion').length;

                    if ( !self.options.selectFirstSuggestion ) return;
                    self.selectFirstSuggestion();
                }else{
                    self.options.totalSuggestions = 0;
                    // container.removeClass('has-items').append('<div class="kk-suggestion"><p>No Matches Found</p></div>');
                    self.options.container.find('span.kk-suggestions').html('');
                    self.suggestionOpen();
                    if ( !self.options.showNoMatchesFound ) return;
                    self.options.container.find('span.kk-suggestions').removeClass('has-items')
                            .append('<div class="kk-message">'+ self.options.noMatchesFoundMsg +'</div>');
                            console.log("No Matches Found")
                }

            }).fail(function(e) {
                if (typeof self.options.onFail === 'function') {
                    self.options.onFail.apply(self.elem, arguments);
                };
            });
        },

        fetch: function() {

            return $.ajax({
                url: this.options.url,
                type: this.options.type,
                dataType: this.options.dataType,
                data: this.options.data,
            });
        },

        display: function(results) {
            var self = this;
            
            if (typeof self.options.onDisplay === 'function') {
                self.options.onDisplay.apply(self.elem, arguments);
            };
            if (typeof results.results == 'undefined' || results.results == null) return false;

            if ( results.length !=0 && results!=null && typeof results.results != 'undefined' ) {
                // self.options.container.find('.kk-dropdown-menu').addClass('open');
                // if ( results.results == null && results.results.length == 0 ) return;
                
                self.suggestionOpen()
                self.options.container.find('span.kk-suggestions').html('');
                $.each(results.results, function(index, val) {
                    if ( $.trim(val.value).length !=0 ) {
                        var valueStr = $("<span/>", { text: val.value }).html();
                        self.options.container.find('span.kk-suggestions').addClass('has-items')
                        .append("<div class='kk-suggestion'><p><span class='value'>"+ valueStr +"</span></p></div>");
                    };
                });
            }
        },

        selectFirstSuggestion: function () {
            var self = this;
                var $kkSuggestion = self.options.container.find('.kk-suggestion').removeClass('kk-is-under-cursor')
                    .eq(0).addClass('kk-is-under-cursor');
                self.setData();
        },

        limit: function(obj, count) {

        },

        renderBaseTpl: function() {
            var self = this;
            var baseTpl = [];
            baseTpl = ['<span class="kk-suggest-wrap"></span>'];

            self.$elem.wrap(baseTpl.join('')).css({
                "position": 'relative',
                'vertical-align': 'top',
                // 'background-color': 'transparent',
            }).parents('.kk-suggest-wrap')
                .append('<span class="kk-dropdown-menu" style="position: absolute; top: 100%; left: 0px; z-index: 100; right: auto;">' +
                    '<div class="kk-dataset-query" style="display: block;"> <span class="kk-suggestions" style="display: block;">  </span> </div> </span>');
            
            self.options.container = self.$elem.parents('.kk-suggest-wrap');                

        },
        closeOnEscape: function () {
            var self = this;        
            $(document).keyup(function(e) {
                if ( !self.$elem.is(":focus") ) return;
                if (e.keyCode == 27) { self.suggestionClose() }   // esc
            });

            $(document).click(function(e) {
                if ( !self.$elem.is(":focus") ) return;
                if ( !$(e.target).is('#'+self.$elem.attr('id') || '.' + self.$elem.attr('class')) ) {
                    self.suggestionClose();
                }
            });
        }


    };

    $.fn.kTypeAhead = function(options) {
        return this.each(function() {

            var kTypeAhead = Object.create(kTypeahead);
            kTypeAhead.init(options, this);

            $.data(this, 'kTypeAhead', kTypeAhead);
        });
    };

    $.fn.kTypeAhead.options = {
        // table: 'users',
        url: null,
        type: 'POST',
        data: {
            limit: 10,
            query: null
        },
        dataType: 'json',

        template: '<li></li>',
        searchBtn: '',
        container: '',
        onFetch: null,
        onComplete: null,
        onFail: null,
        onStartTyping: null,
        onTyping: null,
        onEnter: null,
        onSuggestionClick: null,
        totalRows: 0,
        totalSuggestions: 0,

        closeSuggestionOnBlur: true,
        selectFirstSuggestion: true,
        onDisplay: null,
        customDisplay: false,
        keyUpAndDownSelect: true,
        showNoMatchesFound: false,
        noMatchesFoundMsg: 'No Matches Found',

        curTextHint: '',
        curTextType: '',

        delay: 400,
        doSuggestionOnStop: true,
    };

})(jQuery, window, document);