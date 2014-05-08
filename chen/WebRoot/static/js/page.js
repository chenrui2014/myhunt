function setPage(opt){
		if(!opt.pageDivId || opt.allPageNum < opt.curpageNum ){
			return false;
		};
		var allPageNum = opt.allPageNum; //总的页数
		var showPageNum = opt.showPageNum; //显示的页数
		var curpageNum = opt.curpageNum; // 当前的页数
		
		var pageDIvBox = document.getElementById(opt.pageDivId);
		pageDIvBox.innerHTML = '';
		
		
		//左边或右边显示页码的个数
		var lrNum = Math.ceil(showPageNum/2); 
		if(curpageNum>1){
			var oA = document.createElement('a');
				oA.href='#1';
				oA.innerHTML = '首页';
				pageDIvBox.appendChild(oA);
		}
		
		if(curpageNum>1){
			var oA = document.createElement('a');
				oA.href='#'+(curpageNum-1);
				oA.innerHTML = '上一页';
				pageDIvBox.appendChild(oA);
		}
		
		if(curpageNum<showPageNum-2 || allPageNum == showPageNum){
			for(var i=1;i<=allPageNum;i++){
				var oA = document.createElement('a');
				oA.href = '#'+i;
				if(curpageNum==i){
					oA.innerHTML = i;	
				}else{
					oA.innerHTML = "[" + i + "]";	
				}		
				pageDIvBox.appendChild(oA);
			}	
		}else{
			//倒数第一页的处理
			if(allPageNum-curpageNum<lrNum && curpageNum == allPageNum-1){
				for(var i=1;i<=showPageNum;i++){
					console.log((curpageNum - showPageNum + i));
					var oA = document.createElement('a');
					oA.href = '#'+ (curpageNum - (showPageNum-1) + i);
					if(curpageNum == (curpageNum - (showPageNum-1) + i)){
						oA.innerHTML = (curpageNum - (showPageNum-1) + i);
					}else{
						oA.innerHTML = '['+(curpageNum - (showPageNum-1) + i)+']';
					}
					pageDIvBox.appendChild(oA);
				}
			}
			//最后一页的处理
			else if(allPageNum-curpageNum<lrNum && curpageNum == allPageNum){
				for(var i=1;i<=showPageNum;i++){
					console.log((curpageNum - showPageNum + i));
					var oA = document.createElement('a');
					oA.href = '#'+ (curpageNum - showPageNum + i);
					if(curpageNum == (curpageNum - showPageNum + i)){
						oA.innerHTML = (curpageNum - showPageNum + i);
					}else{
						oA.innerHTML = '['+(curpageNum-showPageNum + i)+']';
					}
					pageDIvBox.appendChild(oA);
				}
			}else{
				for(var i=1;i<=showPageNum;i++){
					var oA = document.createElement('a');
					oA.href = '#'+ 	(curpageNum - (showPageNum-lrNum) + i);
					if(curpageNum == (curpageNum - (showPageNum-lrNum) + i)){
						oA.innerHTML = (curpageNum - (showPageNum-lrNum) + i);
					}else{
						oA.innerHTML = '['+(curpageNum - (showPageNum-lrNum) + i)+']';
					}
					pageDIvBox.appendChild(oA);
				}
			}
		}
		
		if(curpageNum<allPageNum){
			for(var i=1;i<=2;i++){
				if(i==1){
					var oA = document.createElement('a');
					oA.href='#'+(parseInt(curpageNum)+1);
					oA.innerHTML = '下一页';
				}else{
					var oA = document.createElement('a');
					oA.href='#'+allPageNum;
					oA.innerHTML = '尾页';

				}
				pageDIvBox.appendChild(oA);
			}
		}
		
		var oA =  $('#page a');//document.getElementsByTagName('a');
		//给页码添加点击事件
		for(var i=0;i<oA.length;i++){
			oA[i].onclick = function(){
				//当前点的页码
				var sHref = this.getAttribute('href').substring(1);
				//清空页数显示
				pageDIvBox.innerHTML = '';
				loadData(sHref);
				 /* setPage({
					pageDivId:'page',
					showPageNum:showPageNum, //显示的个数
					allPageNum:allPageNum, //总页数
					curpageNum:sHref //当前页数
				}); */ 
			};
		}
	}