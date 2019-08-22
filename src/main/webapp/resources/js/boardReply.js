/**
 * 
 */
console.log('reply module')

var replyService = (function(){
		
		function add(commentInfo,callback,error){
			
		$.ajax({
			
			url : "/video_course-project/board/commentWrite",
			type : "POST",
			contentType : "application/json; charset=UTF-8",
			async : true,
			data : JSON.stringify(commentInfo),
			success : function(result,status,xhr) {
				if(callback){
					callback(result)
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		})	
	}
		
		function getlist(bno,callback,error){
			
			$.getJSON("/video_course-project/board/commentList/"+bno
				,function(data){
					if(callback){
						callback(data)
					}
			}).fail(function(xhr,status,err){
				if(error){
					error();
				}
			});
		}
		
		function displayTime(timeValue){
			var today = new Date();
			
			var gap = today.getTime() - timeValue;
			
			var dateObj = new Date(timeValue)
			
			var str="";
			
			if(gap<(1000*60*60*24)){
				var hh = dateObj.getHours();
				var mi = dateObj.getMinutes();
				var ss = dateObj.getSeconds();
				
				return [(hh>9?'':'0')+hh, ":", (mi>9?'':'0') + mi, ':', (ss>9? '' : '0')+ss].join('');
			}else{
				var yy = dateObj.getFullYear();
				var mi = dateObj.getMinutes();
				var ss = dateObj.getSeconds();
				
				return [yy, '/', (mm>9? '' : '0')+mm,'/',(dd>9? '' : '0')+ dd].join('')
			}
		}
	
	return{
		add: add,
		getlist: getlist,
		displayTime: displayTime
	};
})();