/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


CKEDITOR.editorConfig = function (config)
{
    config.removePlugins = 'elementspath,resize';
    config.allowedContent = true;
    config.removePlugins = 'elementspath,resize';
    config.toolbarGroups = [
        {"name": "basicstyles","groups": ["basicstyles"]},
        {"name": "links","groups": ["links"]},
        {"name": "paragraph","groups": ["list", "blocks"]},
     //   {"name": "document","groups": ["mode"]},
        {"name": "insert","groups": [[ 'Table']]},
        {"name": "styles","groups": ["styles"]}
    ];
    removeButtons: 'Underline,Strike,Subscript,Superscript,Anchor,Styles,Specialchar';
};


