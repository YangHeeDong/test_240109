<script>
	import { page } from '$app/stores';
	import { onMount } from 'svelte';
	import { browser } from '$app/environment';
	import { fromJSON } from 'postcss';
	import rq from '$lib/rq/rq.svelte.js';
	
	let article = $state([]);

	
	let memberId = $state(0);
	let memberNickname = $state("");

	const id = $page.params['id'];

	onMount( async () => {
		// 페이지 매개변수로부터 id 추출
		

		fetch('http://localhost:8080/api/v1/articles/' + id)
			.then((response) => response.json())
			.then((json) => (article = json.data.article));

		await rq.initAuth();
		memberId = await rq.getMember().id;
		memberNickname = await rq.getMember().nickname;
	});

	let formData = $state({
		id : id,
		title: '',
		content: ''
  	});

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
				method : "PUT",
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

<h3>Modify Article</h3>
<form on:submit|preventDefault={submitArticleForm}>
    <div class="input-group">
        <span class="input-group-text bg-dark text-white border-dark">Title</span>
        <input type="text" name="title" class="form-control border-dark" bind:value={formData.title} aria-describedby="basic-addon1">
    </div>

    <div class="input-group mt-1">
        <span class="input-group-text bg-dark text-white border-dark">body</span>
        <textarea name="body" class="form-control border-dark" aria-label="With textarea" rows="20" bind:value={formData.content}>{article.content}</textarea>
    </div>

    <div class="mt-2">
        <a class="btn-sm btn-primary text-decoration-none " href="/article/list">목록으로</a>
        <button type="submit" class="btn-sm bg-dark text-white  text-decoration-none float-end ">수정</button>
    </div>
</form>
