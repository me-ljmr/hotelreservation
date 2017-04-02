/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * owner : Luja Manandhar
 * **/

function addToViewCard(id, filedata) {

        var x = document.createElement("li");
        x.setAttribute("id", id);
        x.setAttribute("style", "opacity:0.5;")
        var img = document.createElement("img");
        img.setAttribute("src", filedata);
        x.appendChild(img);
        
        //input type="hidden" name="picture" value="data"
        var y = document.createElement("input");
        y.setAttribute("type","hidden");
        y.setAttribute("name","picture");
        y.setAttribute("value", filedata);
        
        // add it to viewcard
        document.getElementById("viewcard").appendChild(x);
        document.getElementById("viewcard").appendChild(y);
    }
    
var  getRandomizedString=function( ) {
    var length=6;
    var chars ='0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.round(Math.random() * (chars.length - 1))];
    return result;
};
    generateId = function(){
            return getRandomizedString();
        };
        ibrowse=function(){
            $("#picbrowser").click();
        }
    $("#picbrowser").bind("change", function() {
            var files  = document.getElementById("picbrowser").files;
            var tobeuploadedpics= [];
            for(var i=0;i<files.length;i++){
                var reader = new FileReader();
                reader.readAsDataURL(files[i]);
                reader.onload =
                    function(e)
                    {
                        // uploaded file action here
                        var id = generateId();
                        tobeuploadedpics.push(e.target.result);
                        addToViewCard(id,e.target.result); 
                        console.log(tobeuploadedpics);
 
                    };
                }
        });
        
        showDock=function(dockid){
                $("#dock_" + dockid).show();
            };
        hideDock=function(dockid){
                $("#dock_" + dockid).hide();
            };
        deleteClicked=function(mediaid){
                ondelete(mediaid);
            };
            thumbpress=function(imagefile){

                showpreview = true;
                largephoto = imagefile;

            }