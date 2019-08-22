/**
 * 
 */
$('#selectBtn').click(function(){
			const checkbox = $("input[name=contentCheckbox]:checked");
			const changeBtn = $("#changeBtn");
			const groupName = $('#groupName').val();
			const selectedBox = $('#selectBox option:selected').val();
			console.log(selectedBox);
			
			if(selectedBox===null||selectedBox===""){
				alert('해당 강의의 그룹을 선택하세요');
				return false;
			}
			
			var rowData = new Array();
			checkbox.each(function(e){
				const videoNumber = $(this).parent().children('.videoNumber').val();
				const videoGroup = $(this).parent().children('.videoGroup').val();

				if(videoGroup==selectedBox){
					alert('해당 동영상은 이미 같은 그룹입니다.')
					checkbox.prop("checked",false);
					return false;
				}
				
				rowData.push(videoNumber);
				
			})
			console.log(rowData);
			var changeData = {
					'vno' : rowData,
					'groupName' : selectedBox
			}
			changeBtn.click(function(){

				$.ajax({
					url : "/video_course-project/lecturer/changeInform",
					type: "POST",
					contentType: "application/json; charset=UTF-8",
					async : true,
					data: JSON.stringify(changeData),
					success: function(rs,request,xhr){
						var result = parseInt(rs);
						console.log(result);
						if(result===1){
							alert('수정 완료');
							location.href = "/video_course-project/lecturer/lectureUpdateOk";
						}else if(result===0){
							$('errorMsg').html('비디오 그룹을 선택하세요!');
						}else{
							$('errorMsg').html('업데이트에 실패했습니다. 관리자와 상의하세요');
						}
					},
					error : function(er){
						if(error){
							error(er);
						}
					}
				})
			})
		})