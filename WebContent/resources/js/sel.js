



function g(o){return document.getElementById(o);}
function sel(n){
for(var i=1;i<=4;i++){g('tab_'+i).className='normaltab';
g('box_'+i).className='undis';}g('box_'+n).className='dis'; 
g('tab_'+n).className='hovertab';
}
