<script>
    
    import { browser } from '$app/environment';

	let formData = {
		title: '',
		content: ''
  	};

    function submitArticleForm() {

		if(formData.title.trim() == ""){
			alert("제목은 필수여요");
			return;
		}

		if(formData.content.trim() == ""){
			alert("내용은 필수여요");
			return;
		}
		
		fetch("http://localhost:8080/api/v1/articles",{
				method : "POST",
				credentials:'include',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(formData)
			})
			.then(response => {
        		return response.json();
      		})
			.then(rs => {
				window.location.href = "/article/detail/"+rs.data.id;
			});
    }
	
</script>

<h3>Create Article</h3>
<form on:submit|preventDefault={submitArticleForm}>
    <div class="input-group">
        <span class="input-group-text bg-dark text-white border-dark">Title</span>
        <input type="text" name="title" class="form-control border-dark" placeholder="Enter title" bind:value={formData.title} aria-describedby="basic-addon1">
    </div>

    <div class="input-group mt-1">
        <span class="input-group-text bg-dark text-white border-dark">body</span>
        <textarea name="body" class="form-control border-dark" aria-label="With textarea" rows="20" bind:value={formData.content} placeholder="Enter body"></textarea>
    </div>

    <div class="mt-2">
        <a class="btn-sm btn-primary text-decoration-none " href="/article/list">목록으로</a>
        <button type="submit" class="btn-sm bg-dark text-white  text-decoration-none float-end ">등록</button>
    </div>
</form>
