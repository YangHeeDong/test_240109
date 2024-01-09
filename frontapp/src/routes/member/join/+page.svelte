<script>
    
    import { browser } from '$app/environment';

	let formData = {
		loginId: '',
		password: '',
		passwordConfirm: '',
		email: '',
		nickname: ''
  	};

    function submitJoinForm() {

		if(formData.loginId.trim() == ""){
			alert("아이디를 입력해주세요");
			return;
		}

		if(formData.password.trim() == ""){
			alert("비밀번호를 입력해주세요");
			return;
		}
		if(formData.passwordConfirm.trim() == ""){
			alert("비밀번호 확인을 입력해주세요");
			return;
		}
		if(formData.email.trim() == ""){
			alert("이메일을 입력해주세요");
			return;
		}
		if(formData.nickname.trim() == ""){
			alert("닉네임을 입력해주세요");
			return;
		}

		if(formData.password.trim() != formData.passwordConfirm.trim()){
			alert("비밀번호와 비밀번호 확인이 일치하지 않아요");
			return;
		}
		
		fetch("http://localhost:8080/api/v1/member/join",{
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
				// 회원가입 후 처리
				alert(rs.msg);
				if(rs.success){
					window.location.href = "/member/login";	
				}
			});
    }
	
</script>

<h3>Join</h3>
<form on:submit|preventDefault={submitJoinForm}>
    <div class="input-group">
        <span class="col-2 justify-content-center  input-group-text bg-dark text-white border-dark">LoginId</span>
        <input type="text" name="loginId" class="form-control border-dark" placeholder="Enter LoginId" bind:value={formData.loginId} aria-describedby="basic-addon1">
    </div>

    <div class="input-group mt-1">
        <span class="col-2 justify-content-center input-group-text bg-dark text-white border-dark">Password</span>
        <input type="password" name="password" class="form-control border-dark" bind:value={formData.password} placeholder="Enter Password">
    </div>

	<div class="input-group mt-1">
        <span class="col-2 justify-content-center input-group-text bg-dark text-white border-dark">PasswordConfrim</span>
        <input type="password" name="passwordConfirm" class="form-control border-dark" bind:value={formData.passwordConfirm} placeholder="Enter Password Confrim">
    </div>

	<div class="input-group mt-1">
        <span class="col-2 justify-content-center input-group-text bg-dark text-white border-dark">Email</span>
        <input type="email" name="email" class="form-control border-dark" bind:value={formData.email} placeholder="Enter email">
    </div>

	<div class="input-group mt-1">
        <span class="col-2 justify-content-center input-group-text bg-dark text-white border-dark">NickName</span>
        <input type="text" name="nickname" class="form-control border-dark" bind:value={formData.nickname} placeholder="Enter Nickname">
    </div>



    <div class="mt-2">
        <a class="btn-sm btn-primary text-decoration-none " href="/article/list">목록으로</a>
        <button type="submit" class="btn-sm bg-dark text-white  text-decoration-none float-end ">가입</button>
    </div>
</form>
