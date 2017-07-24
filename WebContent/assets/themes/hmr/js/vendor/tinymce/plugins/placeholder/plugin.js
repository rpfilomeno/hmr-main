tinymce.PluginManager.add('placeholder', function(editor) {
    editor.on('init', function() {
        var label = new Label;
        
        onBlur();

        tinymce.DOM.bind(label.el, 'click', onFocus);
        editor.on('focus', onFocus);
        editor.on('blur', onBlur);

        function onFocus(){
            label.hide();
            tinyMCE.execCommand('mceFocus', false, editor);
        }

        function onBlur(){
            // console.log(editor.getContent())
            if(editor.getContent() == '') {
                label.show();
            }else{
                label.hide();
            }
        }
    });

    var Label = function(){
        // Create label el
        this.text = editor.getElement().getAttribute("placeholder");
        console.log(this.text)
        this.contentAreaContainer = editor.getContentAreaContainer();
        // console.log( $(editor.getElement()).addClass('class_nametessssssst') )

        tinymce.DOM.setStyle(this.contentAreaContainer, 'position', 'relative');

        attrs = {style: {position: 'absolute', top:'7px', left:'8px', color: '#888', padding: '1%', width:'98%', overflow: 'hidden', 'font-family': 'inherit', 'font-size': 'inherit'}, class:"tiny-placeholder" };
        this.el = tinymce.DOM.add( this.contentAreaContainer, "label", attrs, this.text );
        // console.log(this.contentAreaContainer)
        // console.log(this.el)
    }

    Label.prototype.hide = function(){
        tinymce.DOM.setStyle( this.el, 'display', 'none' );
        // console.log("hide")
    }

    Label.prototype.show = function(){
        // console.log("show")
        tinymce.DOM.setStyle( this.el, 'display', '' );   
    }
});