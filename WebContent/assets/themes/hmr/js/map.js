pulseMarker.prototype = new google.maps.OverlayView();

function pulseMarker(opts) {
    this.setValues(opts);
}

pulseMarker.prototype.draw = function() {
    var self = this;
    var div = this.div;
    if (!div) {
        div = this.div = $('' +
            '<div>' +
            '<div class="shadow"></div>' +
            '<div class="pulse"></div>' +
            '<div class="pin-wrap">' +
            '<div class="pin"></div>' +
            '</div>' +
            '</div>' +
            '')[0];
        this.pinWrap = this.div.getElementsByClassName('pin-wrap');
        this.pin = this.div.getElementsByClassName('pin');
        this.pinShadow = this.div.getElementsByClassName('shadow');
        div.style.position = 'absolute';
        div.style.cursor = 'pointer';
        var panes = this.getPanes();
        panes.overlayImage.appendChild(div);
        google.maps.event.addDomListener(div, "click", function(event) {
            google.maps.event.trigger(self, "click", event);
        });
    }
    var point = this.getProjection().fromLatLngToDivPixel(this.position);
    if (point) {
        div.style.left = point.x + 'px';
        div.style.top = point.y + 'px';
    }
};

var phLong = '12.4000',
    // phLat = '121.9267',
    phLat = '115',
    markers = [],
    dirMarkers = [],
    dirInfowindow,
    infowindow,
    infowindows = [],
    placeMarkers = [],
    markerCluster,
    service,
    geocoder,
    hotSpotList,
    map, base_url = $('#base_url').val(),
    sF = $('#search-input');

var mapJs = {
    init: function () {
        this.getHotspots();
        this.map();
        this.bindEvents();
    },
    bindEvents: function () {
        mapJs.mapOnTilesLoaded();
        mapJs.mapOnResized();
        mapJs.mapOnDblClick();


        $('#hotspot-main-card').css( 'max-height', $('#map-canvas').height() - 100 );
    },
    mapOptions: function () {

        if ( $(window).width() < 760 ) {
            // console.log( $(window).width() )
            phLat = '121.9267';
        }

        var mapOptions = {
            zoom: 6,
            center: new google.maps.LatLng(phLong, phLat),
            // mapTypeId: google.maps.MapTypeId.HYBRID,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            mapTypeControl: false,
            mapTypeControlOptions: {
                // style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
                position: google.maps.ControlPosition.RIGHT_TOP,
            },
            panControl: false,
            panControlOptions: {
                position: google.maps.ControlPosition.TOP
            },
            zoomControl: true,
            zoomControlOptions: {
                // style: google.maps.ZoomControlStyle.SMALL,
                position: google.maps.ControlPosition.RIGHT_CENTER,

            },
            scaleControl: false,
            scaleControlOptions: {
                position: google.maps.ControlPosition.RIGHT_TOP
            },
            streetViewControl: false,
            streetViewControlOptions: {
                position: google.maps.ControlPosition.RIGHT_TOP
            },
            draggableCursor: 'default',
            scrollwheel: false,
            styles: [{"featureType":"all","elementType":"labels.text.fill","stylers":[{"saturation":36},{"color":"#000000"},{"lightness":40}]},{"featureType":"all","elementType":"labels.text.stroke","stylers":[{"visibility":"on"},{"color":"#000000"},{"lightness":16}]},{"featureType":"all","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"administrative","elementType":"geometry.fill","stylers":[{"color":"#000000"},{"lightness":20}]},{"featureType":"administrative","elementType":"geometry.stroke","stylers":[{"color":"#000000"},{"lightness":17},{"weight":1.2}]},{"featureType":"landscape","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":20}]},{"featureType":"poi","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":21}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#000000"},{"lightness":17}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"color":"#000000"},{"lightness":29},{"weight":0.2}]},{"featureType":"road.arterial","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":18}]},{"featureType":"road.local","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":16}]},{"featureType":"transit","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":19}]},{"featureType":"water","elementType":"geometry","stylers":[{"color":"#000000"},{"lightness":17}]}]
        };
        return mapOptions;  
    },
    map: function() {

        // mapJs.refreshMap();
        // console.log("map called");
        map = new google.maps.Map(document.getElementById('map-canvas'), mapJs.mapOptions());

        var pos = new google.maps.LatLng(14.651111, 120.971716);

        var marker = new pulseMarker({
            position: pos,
            map: map,
        });

       
        
        // google.maps.event.addDomListener(window, 'load', mapJs.map);
        mapJs.mapControls();
        mapJs.onMapZoom();
    },

    getHotspots: function() {
        
        $.ajax({
             url: base_url + 'q/hotspot',
             type: 'POST',
             dataType: 'json',
             data: {
                limit: null,
                offset: null,
                select: 'id, place_name, place_type, longitude, latitude',
             },
        })
        .fail(function(e) {
            console.log(e.responseText)
            console.log("error");
        })
        .always(function(data) {
            // console.log(data)

            // console.log( typeof data.results )

            if ( typeof data.results != "undefined" ) {
                $.each(data.results, function(index, val) {

                    $('#hotspot-list').append('<li><a id="hotspot-item-'+ val.id +'" data-id="'+ val.id +'" data-place-name="'+ val.place_name +'" data-place-type="'+ val.place_type +'" href="#"><span class="element-label">'+ val.place_name +'</span> <span class="pull-right text-muted small">'+ val.place_type +'</span></a></li>');


                    var stype = val.place_type;
                    var position = new google.maps.LatLng(val.longitude, val.latitude);
                    var content = mapJs.render('infowindow',{
                                                    name: val.place_name,
                                                    type: '<span class="ttc">' + stype + '</span>',
                                                    content: '<div class="clearfix"></div>'
                                                                
                                                });

                    

                    
                     var marker = new google.maps.Marker({
                        position: position,
                        icon: new google.maps.MarkerImage(base_url + 'img/hatol/map-marker.svg',null, null, null, new google.maps.Size(40,40)),
                        map: map,
                        title: val.place_name,
                        clickable: true,
                        hotspot_data: {
                            place_name: val.place_name,
                            id: val.id,
                        }
                    });           

                    marker.metadata = {id: val.id}; 

                    placeMarkers.push(marker);


                    google.maps.event.addListener(marker,'click', function() {
                        
                         if (infowindow) {
                            infowindow.close();
                        }

                        $('#hotspot-item-'+ marker.hotspot_data.id).click();

                        // map.setZoom(8);
                        // map.setCenter(marker.getPosition());

                        infowindow = new google.maps.InfoWindow();
                        infowindow.setContent(content);
                        infowindow.open(map,marker);

                        // $('#hotspot-main-card').addClass('hide');

                    });  
               

                    // add pulse
                    setTimeout(function() {
                        var pMarker = new pulseMarker({
                            position: position,
                            map: map,
                        });
                    }, 100 * index);

                    

                });

                var options = {
                    valueNames: [ 'element-label' ]
                };

                hotSpotList = new List('hotspot-list-wrap', options);
                var markerCluster = new MarkerClusterer(map, placeMarkers);


                $('#hotspot-list > li > a').live('click', function(e) {
                    e.preventDefault();
                    var $this = $(this);

                    $('#map-preloader').show().addClass('active')

                    $('#hotspot-list li').removeClass('active');
                    $this.parents('li').addClass('active');

                    mapJs.getHotSpotData({
                        id: $this.data('id'),
                        place_type: $this.data('place-type'),
                        place_name: $this.data('place-name'),
                    });

                });

                $('.hotspot-search-input').on('focus', function() {
                    $('#hotspot-list').show();
                });
            }
            // console.log("complete");
        });



    },

    getHotSpotData: function(params) {
        
        $.ajax({
            url: base_url + 'q/hotspot',
            type: 'POST',
            dataType: 'json',
            data: {
                id: params.id,
                select: 'general_info, vote_pop, issues, elec_result',
            },
        })
        .fail(function(e) {
            console.log(e.responseText)
            console.log("error");
        })
        .always(function(data) {
            // console.log(data)

            $('#map-preloader').removeClass('active');
            setTimeout(function () {
                $('#map-preloader').css('display', 'none');
            }, 600);

            if ( typeof data.results != "undefined" ) {

                $("#hotspot-data-wrap").removeClass('hide');
                $('.cur-place-type').text(params.place_type)
                $('.cur-place-name').text(params.place_name)
                $('#general-information-tab .ms-panel').html( '<div class="hotspot-graphs"></div>' + data.results.general_info );
                $('#voting-population-tab .ms-panel').html( '<div class="hotspot-graphs"></div>' + data.results.vote_pop );
                $('#election-result-tab .ms-panel').html( data.results.elec_result );
                $('#issues-tab .ms-panel').html( data.results.issues );

                $('#hotspot-main-card').animate({
                   scrollTop: 0
                }, 'slow');

                $('.ms-panel').animate({
                   scrollTop: 0
                }, 'slow');

                $('.hotspot-search-input').val( params.place_name )
                $('#hotspot-list').hide();

                setTimeout(function() {
                    $('#hotspot-main-card').removeClass('hide');
                }, 800);

                mapJs.getGraph(params);
                mapJs.getCandidates(params);

              
            };
            
        });
            
    },

    getGraph: function(params) {
        
        $.ajax({
            url: base_url + 'q/hotspot_graph',
            type: 'POST',
            dataType: 'json',
            data: {
                hotspot_id: params.id
            },
        })
        .fail(function(e) {
            console.log(e.responseText)
            console.log("error");
        })
        .always(function(data) {
            // console.log(data)

            if ( typeof data.results != "undefined" ) {
                $('#general-information-tab .hotspot-graphs').html('');
                $.each(data.results, function(index, val) {
                    if ( val.data_type == "4PS" || val.data_type == "Education" ) {
                        $('#general-information-tab .hotspot-graphs').prepend( val.graph );    
                    }
                    if ( val.data_type == "Election" ) {
                        $('#voting-population-tab .hotspot-graphs').prepend( val.graph );
                    }
                    
                });
                             
            };
            
        });

    },

    getCandidates: function(params) {
        
        $.ajax({
            url: base_url + 'q/hotspot_candidates',
            type: 'POST',
            dataType: 'json',
            data: {
                hotspot_id: params.id,
                select: 'name, position, partylist'
            },
        })
        .fail(function(e) {
            console.log(e.responseText)
            console.log("error");
        })
        .always(function(data) {
            // console.log(data)

            if ( typeof data.results != "undefined" ) {
                $('#hotspot-candidates-tab .ms-panel').html('<ol class="hotspot-candidates-list top20"></ol>');
                $.each(data.results, function(index, val) {
                    
                    $('#hotspot-candidates-tab .ms-panel .hotspot-candidates-list').append( '<li><span class="candidate-name">'+val.name+'</span><span class="candidate-position">'+ val.position +'</span>, <span class="candidate-partylist">'+ val.partylist +'</span></li>' );

                });
                             
            };
            
        });

    },

    geocoder: function (latLng, geocoderCallback) {
            geocoder = new google.maps.Geocoder();
            geocoder.geocode({'latLng': latLng}, geocoderCallback);
    },

    refreshMap: function () {
        google.maps.visualRefresh = true;
    },

    mapOnTilesLoaded: function () {
       
         google.maps.event.addListenerOnce(map, 'tilesloaded', function () {
            $('#map-preloader').removeClass('active');

            setTimeout(function () {
                $('#map-preloader').css('display', 'none');
            }, 600);
        });

    },
    mapOnResized: function () {
      google.maps.event.addDomListener(window, "resize", function() {
            var center = map.getCenter();
            google.maps.event.trigger(map, "resize");
            map.setCenter(center);
        });  
    },
    resetQdata: function () {
        sF.val('');
        sF.removeData('value');
        sF.removeData('id');
        // sF.removeData('province-id');
        // sF.removeData('barangay-id');
        // sF.removeData('city-muni-id');
        sF.removeData('long');
        sF.removeData('lat');
        sF.removeData('zoom');
        sF.removeData('type');
        gis.setQdata();
    },
    mapOnDblClick: function () {

        google.maps.event.addListener(map, "dblclick", function(event) {
            var lat = event.latLng.lat();
            var lng = event.latLng.lng();
            console.log("Lat=" + lat + "; Lng=" + lng);
            mapJs.resetQdata();
            var latLng = new google.maps.LatLng(parseFloat(lat), parseFloat(lng));
            
            mapJs.geocoder(latLng, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                  if (results[1]) {
                    // console.log(results[1]);

                    var clickSearch = results[1].address_components[1].long_name;
                    // console.log(clickSearch);

                    if ( clickSearch != sF.val() ) {
                        // mapJs.modal("open");
                        sF.removeClass('searched');
                        sF.val(clickSearch);
                        // $('#search-btn').click();
                    };
                  } else {
                    console.log('No geocode results found');
                  }
                } else {
                  alert('Geocoder failed due to: ' + status);
                }
            });

        });
    },
    mapControls: function () {
        $('.zoom-control').live('click', function (e) {
            var $this = $(this)
            ,   zoomType = $this.data('type')
            ,   zoomLevel = map.getZoom();

            if ( zoomType == "zoom-in" ) {
                map.setZoom(zoomLevel + 1);
            }else{
                map.setZoom(zoomLevel - 1);
            }
            e.preventDefault()
        });  

        $('.map-styles').live('click', function (e) {
            map.set('styles', mapJs.mapStyles('neutral-blue'));
            e.preventDefault();
        });
    },
    mapStyles: function (style) {
        var design;
        switch (style){
            case 'neutral-blue':
            design = [{"featureType": "water", "elementType": "geometry", "stylers": [{"color": "#193341"} ] }, {"featureType": "landscape", "elementType": "geometry", "stylers": [{"color": "#2c5a71"} ] }, {"featureType": "road", "elementType": "geometry", "stylers": [{"color": "#29768a"}, {"lightness": -37 } ] }, {"featureType": "poi", "elementType": "geometry", "stylers": [{"color": "#406d80"} ] }, {"featureType": "transit", "elementType": "geometry", "stylers": [{"color": "#406d80"} ] }, {"elementType": "labels.text.stroke", "stylers": [{"visibility": "on"}, {"color": "#3e606f"}, {"weight": 2 }, {"gamma": 0.84 } ] }, {"elementType": "labels.text.fill", "stylers": [{"color": "#ffffff"} ] }, {"featureType": "administrative", "elementType": "geometry", "stylers": [{"weight": 0.6 }, {"color": "#1a3541"} ] }, {"elementType": "labels.icon", "stylers": [{"visibility": "off"} ] }, {"featureType": "poi.park", "elementType": "geometry", "stylers": [{"color": "#2c5a71"} ] }]
            break;

            case 'cool-grey':
            design = [{"featureType": "landscape", "elementType": "labels", "stylers": [{"visibility": "off"} ] }, {"featureType": "transit", "elementType": "labels", "stylers": [{"visibility": "off"} ] }, {"featureType": "poi", "elementType": "labels", "stylers": [{"visibility": "off"} ] }, {"featureType": "water", "elementType": "labels", "stylers": [{"visibility": "off"} ] }, {"featureType": "road", "elementType": "labels.icon", "stylers": [{"visibility": "off"} ] }, {"stylers": [{"hue": "#00aaff"}, {"saturation": -100 }, {"gamma": 2.15 }, {"lightness": 12 } ] }, {"featureType": "road", "elementType": "labels.text.fill", "stylers": [{"visibility": "on"}, {"lightness": 24 } ] }, {"featureType": "road", "elementType": "geometry", "stylers": [{"lightness": 57 } ] } ] 
            break;

            case 'cobalt':
            design = [{"featureType":"all","elementType":"all","stylers":[{"invert_lightness":true},{"saturation":10},{"lightness":30},{"gamma":0.5},{"hue":"#435158"}]}]
            break;


        };
        return design;
    },

    onMapZoom: function () {
        // console.log(map.getZoom())
        google.maps.event.addListener(map, 'zoom_changed', function() {
            var iconSize;
            var zoomLevel = map.getZoom();

            var NewMapCenter = map.getCenter();
            var latitude = NewMapCenter.lat();
            var longitude = NewMapCenter.lng();
            console.log("Lat:" + latitude)
            console.log("Long:" + longitude)

            if ( zoomLevel <= 6 ) {
                iconSize = new google.maps.Size(20, 26);
            };
            if ( zoomLevel == 7 ) {
                iconSize = new google.maps.Size(30, 39);
            };
            if ( zoomLevel >= 8 ) {
                iconSize = new google.maps.Size(40, 52);
            };
            if ( zoomLevel >= 9 ) {
                iconSize = new google.maps.Size(50, 65);
            };

            console.log( zoomLevel );
            for(i=0; i< dirMarkers.length; i++ ) {
                var icon = dirMarkers[i].getIcon();
                dirMarkers[i].setIcon(new google.maps.MarkerImage(
                    icon.url, null, null, null, iconSize));
            }



        });

        // google.maps.event.addListener(marker, 'dragend', function() { 
        //     alert('marker dragged'); 
        // });

        // google.maps.event.addListener(map, 'dragend', function() { 
        //     var NewMapCenter = map.getCenter();
        //     var latitude = NewMapCenter.lat();
        //     var longitude = NewMapCenter.lng();
        //     console.log("Lat:" + latitude)
        //     console.log("Long:" + longitude)
        // });
    },
    resizeMap: function (map) {
        mapJs.refreshMap();
    
        var center = map.getCenter();
            google.maps.event.trigger(map, "resize");
            map.setCenter(center);
    },
    locateMap: function (params) {
        var noLongLat = false;

        if ( typeof params.long == 'undefined' && typeof params.lat == 'undefined' ) return false;
        if ( params.long == "0.000000" || params.lat == "0.000000" ) {
            // mapJs.map();
            var latLng = new google.maps.LatLng(13.037317, 122.424204);
            var latLng2 = new google.maps.LatLng(13.037317, 115);
            noLongLat = true;
            
        }else{
            var latLng = new google.maps.LatLng(params.long, params.lat);
            var latLng2 = new google.maps.LatLng(params.long, params.lat - 2);    
            noLongLat = false;
        }
        
        // console.log(params)
        // var tempOffset = new google.maps.LatLng(params.long, params.lat + 10);
            
            map.panTo(latLng2);
            // map.panTo(latLng);

            // map.setZoom(8);

            if ( params.zoom == 0 ) {
                map.setZoom(6);
            }else{
                if ( params.zoom > 8 ) {
                    map.setZoom(8);
                }else{
                    map.setZoom(params.zoom);
                }
            }

            mapJs.resizeMap(map);

        // gis.setAllMap(null);
        // console.log(params)

        mapJs.clearPlaceMarkers();
        google.maps.visualRefresh = true;

        if ( !noLongLat ) {
            var stype = ( params.type == "city-muni" ) ? 'City' : params.type;
            stype = ( params.type == "group_of_islands" ) ? 'Group of Islands' : stype;

            infowindow = new google.maps.InfoWindow({
              content: mapJs.render('infowindow',{
                        name: params.name,
                        type: '<span class="ttc">' + stype + '</span>',
                        content: '<div class="clearfix"></div>'
                                    
                      }),
              // maxWidth: 600
            });

            
             var marker = new google.maps.Marker({
                position: latLng,
                map: map,
                title: params.name,
                clickable: true
            });

            placeMarkers.push(marker);
            infowindow.open(map, marker);

            google.maps.event.addListener(marker, 'click', function() {
              infowindow.open(map, marker);
            });
        };

        mapJs.geocoder(latLng, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
              if (results[1]) {
                // console.log(results);
                // console.log(results)
                // map.setZoom(11);
                // marker = new google.maps.Marker({
                //     position: latLng,
                //     map: map
                // });
                // infowindow.setContent(results[1].formatted_address);
                // infowindow.open(map, marker);
              } else {
                console.log('No geocode results found');
              }
            } else {
              // alert('Geocoder failed due to: ' + status);
            }
        });
       
    },

    clearPlaceMarkers: function () {
        for (var i = 0; i < placeMarkers.length; i++ ) {
            placeMarkers[i].setMap(null);
        }
        placeMarkers.length = 0;
    },
    clearInfowindow: function () {
        for (var i = 0; i < infowindows.length; i++ ) {
            infowindows[i].close()
        }
        infowindows.length = 0;
    },
    render: function(template,params){
        var arr = [];
        switch(template){
            case 'infowindow':
                arr = ['<div class="info-window-wrap">',
                            '<div class="info-win-head"><h1 class="header">', params.name ,'</h1><h2 class="sub-title">', params.type ,'</h2></div>',
                            '<div class="info-win-body">', params.content, '</div>',
                        '</div>'];
        }       
        return arr.join('');
        
    },





};


mapJs.init();








