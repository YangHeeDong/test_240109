<script>
    
    import { browser } from '$app/environment';

	let formData = {
		loginId: '',
		password: ''
  	};

    function submitLoginForm() {

		if(formData.loginId.trim() == ""){
			alert("아이디를 입력해주세요");
			return;
		}

		if(formData.password.trim() == ""){
			alert("비밀번호를 입력해주세요");
			return;
		}
		
		
		fetch("http://localhost:8080/api/v1/member/login",{
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
				alert(rs.msg);
				window.location.href = "/article/list";
			});
    }
	
</script>

<h3>Login</h3>
<form on:submit|preventDefault={submitLoginForm}>
    <div class="input-group">
        <span class="col-2 justify-content-center  input-group-text bg-dark text-white border-dark">LoginId</span>
        <input type="text" name="loginId" class="form-control border-dark" placeholder="Enter LoginId" bind:value={formData.loginId} aria-describedby="basic-addon1">
    </div>

    <div class="input-group mt-1">
        <span class="col-2 justify-content-center input-group-text bg-dark text-white border-dark">Password</span>
        <input type="password" name="password" class="form-control border-dark" bind:value={formData.password} placeholder="Enter Password">
    </div>
    <div class="mt-2">
        <button type="submit" class="btn-sm bg-dark text-white  text-decoration-none float-end ">로그인</button>
    </div>
</form>
