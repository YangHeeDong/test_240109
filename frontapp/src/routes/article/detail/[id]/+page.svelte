<script>
    import { page } from '$app/stores';
    import { onMount } from 'svelte';
    import rq from '$lib/rq/rq.svelte.js';

	import moment from 'moment/min/moment-with-locales'
    moment.locale('ko')
	
	let article = $state([]);

	let memberId = $state(0);
    let memberNickname = $state("");


	onMount(async() => 
	{
		const id = $page.params['id'];

		fetch("http://localhost:8080/api/v1/articles/"+id,{
			method : "GET",
			credentials:'include'
		})
		.then(response => {
        	return response.json();
      	})
		.then(rs => article = rs.data.article);
				
		await rq.initAuth();
		memberId = await rq.getMember().id;
		memberNickname = await rq.getMember().nickname;
	}
	);

	function modifyArticle() {
		window.location.href = '/article/modify/'+article.id;
	}
	
	function deleteArticle() {
		// window.location.href = "/article/detail/"+article.id;
		if (confirm('정말로 삭제??')) {
			fetch('http://localhost:8080/api/v1/articles/' + article.id, {
				method: 'DELETE',
				credentials:'include'
			})
				.then((response) => response.json())
				.then((rs) => {
					alert(rs.msg);
					window.location.href = '/article/list';
				});
		}
	}
	

</script>


<div class="card border-dark mb-2">
	<div class="card-header text-white bg-dark">
		<span>No.{article.id}</span>
		<span class="float-end">{article.nickname}</span>
	</div>
	<div class="card-body text-dark">
		<h5 class="card-title">{article.title}</h5>
		<p class="card-text">{article.content}</p>
		<p class="badge bg-light text-dark float-end ">{moment(article.createDate).format('YYYY-MM-DD')}</p>
	</div>
</div>

<a class="btn-sm btn-primary text-decoration-none" href="/article/list">목록으로</a>
{#if memberId == article.memberId}
<div class="float-end">
	<a class="btn-sm btn-success text-decoration-none" href="#" onclick={modifyArticle}>수정</a>
	<a class="btn-sm btn-danger text-decoration-none" href="#" onclick={deleteArticle}>삭제</a>
</div>
{/if}