<script>

    import { onMount } from 'svelte';
    import rq from '$lib/rq/rq.svelte.js';

    // @ts-ignore
    const { children } = $props();

    let memberId = $state(0);
    let memberNickname = $state("");

    onMount(async () => {
        await rq.initAuth();
        memberId = await rq.getMember().id;
        memberNickname = await rq.getMember().nickname;
	});


    


</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <div class="container">
        <a class="navbar-brand" href="/article/list"><h3>test-240109</h3></a>
        <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon" />
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                {#if memberId == 0}
                    <li class="nav-item">
                        <a class="nav-link" href="/member/join">회원가입</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/member/login">로그인</a>
                    </li>
                {/if}
                {#if memberId != 0}
                    <li>
                        <button style="text-decoration: none; color: inherit;" on:click={() => rq.setLogout()} class="btn btn-link">로그아웃</button>
                    </li>
                {/if}
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-3">
    {@render children()}
</div>