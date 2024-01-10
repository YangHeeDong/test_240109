<script>
	import { onMount } from "svelte";
	import rq from '$lib/rq/rq.svelte.js';

	import moment from 'moment/min/moment-with-locales'
    moment.locale('ko')

	/**
	 * @type {any[]}
	 */
	let articles = $state([]);

    let memberId = $state(0);
    let memberNickname = $state("");

	onMount( async () => 
	{
		fetch("http://localhost:8080/api/v1/articles",{
			method : "GET",
			credentials:'include'
		})
		.then(response => {
        	return response.json();
      	}).then(data => articles = data.data.articleList);
        
        await rq.initAuth();
        memberId = await rq.getMember().id;
        memberNickname = await rq.getMember().nickname;
	}
	);
</script>


<div>
    
    <table class="table text-center">

        <thead class="table-dark">
          <tr class="row">
            <th class="col-1">Id</th>
            <th class="col-9">Title</th>
            <th class="col-2">createDate</th>
          </tr>
        </thead>

        <tbody>
            {#each articles as article}
            <tr class="row">
                <td class="col-1">
                    <span>{article.id}</span>
                </td>
                <td class="col-9 text-start">
                    <a href="/article/detail/{article.id}">{article.title}</a>
                </td>
                <td class="col-2">
                    <span>{moment(article.createDate).format("YYYY-MM-DD")}</span>
                </td>
            </tr>
            {/each}
        </tbody>

      </table>
      {#if memberId !=0}
      <a class="btn-sm btn-primary text-decoration-none float-end " href="/article/create">등록하기</a>
      {/if}
</div>