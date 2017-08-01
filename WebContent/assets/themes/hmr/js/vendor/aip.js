// Utility
if ( typeof Object.create !== 'function' ) {
	Object.create = function ( obj ) {
		function F () {};
		F.prototype = obj;
		return new F();
	}
};

(function ($, window, document, undefined) {
	var AIP = {
		init: function ( options, elem ) {
			var self = this;

			self.elem = elem;
			self.$elem = $( elem );

			self.url = ( typeof options === 'string' )
				? options : options.url;

			self.options = $.extend( {}, $.fn.aip.options, options );
			console.log( self.options )

			self.renderBaseTpl();
			self.scrollEvent();
			self.loadMoreBtn();

			
			

			self.cycle();
		},

		cycle: function () {
			var self = this;

			if ( self.options.is_query ) return;
			console.log("load more!");
			console.log("cycle!");

			self.options.is_query = true;
			
			self.fetch().done(function ( results ) {
				self.buildFrag( results );
				// self.display();
				if ( typeof self.options.onComplete === 'function' ) {
					self.options.onComplete.apply( self.elem, arguments );
				};

			}).fail(function (e) {
				if ( typeof self.options.onFail === 'function' ) {
					self.options.onFail.apply( self.elem, arguments );
				};
			}).always(function () {
				self.options.is_query = false;
			});
		},

		fetch: function () {
			var self = this;
			if ( typeof self.options.onFetch === 'function' ) {
				self.options.onFetch.apply( self.elem, arguments );
			};
			return $.ajax({
				url: self.options.url,
				type: 'post',
				dataType: self.options.dataType,
				data: self.options.data,
			});
		},

		test_method: function () {
			console.log( "test_method" )	
		},
		buildFrag: function ( results ) {
			var self = this;
			if ( results == null ) return;
			self.options.totalRows = results.total_rows;
			self.updateCounts();
			return results;
		},

		display: function () {
			this.$elem.append( this.datum );
		},

		limit: function ( obj, count ) {
			
		},

		renderBaseTpl: function () {
			var self = this;
			var baseTpl = [];
			baseTpl = ['<div class="aip-wrapper"><div class="aip-header"></div>',
						   '<div class="aip-body"></div><div class="aip-footer">',
						   '</div></div>'];

			self.$elem.prepend( baseTpl.join('') );
		},

		scrollEvent: function () {
			var self = this;
			if ( !self.options.triggerByScroll || self.options.triggerByBtn ) return;
			console.log("scrollEvent")

			$(window).scroll(function() {
				if($(window).scrollTop() + $(window).height() == $(document).height()) {

					if ( typeof self.options.onScrollEnd === 'function' ) {
						self.options.onScrollEnd.apply( self.elem, arguments );
					};
					self.getMore();
				}
			});
		},

		loadMoreBtn: function () {
			var self = this;
			if ( !self.options.triggerByBtn ) return;

			console.log("loadMoreBtn")
			self.$elem.find('.aip-footer').append('<button class="btn btn-block btn-default btn-load-more">' + 
				self.options.loadBtnText + ' <span class="pending-count"></span> <span class="total-count"></span></button>');

			self.$elem.find('.btn-load-more').live('click', function (e) {
				self.getMore();
				self.updateCounts();
				e.preventDefault();
			});
		},

		updateCounts: function () {
			// console.log( self.options.data.per_page )
			var self = this;
			var pendingFormula = self.options.totalRows - self.options.data.per_page - self.options.data.offset
			, pendingCount = (pendingFormula > 0) ? pendingFormula : 0;

			self.options.pendingCount = pendingCount;
			self.$elem.find('.pending-count').text(self.options.pendingCount);
			self.$elem.find('.total-count').text( ' of ' + self.options.totalRows );
			// if ( pendingCount == 0 ) {
			// 	self.$elem.find('.btn-load-more').hide();
			// }else{
			// 	self.$elem.find('.btn-load-more').show();
			// }
		},

		getMore: function () {
			// increment offset by per_page
			var self = this;

			if ( self.options.is_query ) return;
			
			self.options.data.offset += self.options.data.per_page
			// console.log(self.options.data)
			if ( self.options.totalRows > self.options.data.offset ) {
				self.cycle();	
			}else{
				console.log("all done")
			}
		}


	};

	$.fn.aip = function ( options ) {
		return this.each(function() {
            var aip = Object.create(  AIP );
			aip.init( options, this);

			$.data( this, 'aip', aip );
        });

		
	};
 
	$.fn.aip.options = {
		// table: 'users',
		template : '<li></li>',
		onComplete: null,
		onFail: null,
		totalRows: 0,
		data: {
			offset: 0,
			per_page: 10,
			filters: {

			} 
		},
		loadBtnText: 'Load More',
		triggerByScroll: true,
		triggerByBtn: false,
		dataType: 'json',
		is_query: false,
	};

})( jQuery, window, document );