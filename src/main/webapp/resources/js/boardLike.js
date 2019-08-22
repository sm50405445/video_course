/**
 * 
 */

console.log('like module')

var likeService = (function(){
	
	function like(listInform,callback,error){
		
		$.ajax({
			url : "/video_course-project/board/heartOn",
			type : "POST",
			contentType : "application/json; charset=UTF-8",
			async : true,
			data : JSON.stringify(listInform),
			success : function(result,status,xhr) {
				if(callback){
					callback(result)
				}
			},
			error : function(xhr,status,er){
				if(error){
					error(er)
				}
			}
		})
	}
	
	return{
		like:like
	}
})();