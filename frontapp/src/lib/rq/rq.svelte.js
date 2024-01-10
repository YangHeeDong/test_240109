class Rq {
    
    member = {
        id : 0,
        nickname : ""
    };

    constructor() {
        this.member = {
            id : 0,
            nickname : ""
        };
    }

    getMember() {
        return this.member;
    }

    /**
     * @param {any} id
     * @param {any} nickname
     */
    setLogined(id,nickname) {
        this.member = {
            id : id,
            nickname : nickname
        };
    }
    setLogout() {
        this.member = {
            id : 0,
            nickname : ""
        }
        window.location.href = "/article/list";
    }

    isLogin() {
        return this.member.id !== 0;
    }

    isLogout() {
        return !this.isLogin();
    }

    async initAuth() {
        await fetch("http://localhost:8080/api/v1/member/me",{
				method : "GET",
				credentials:'include'
			})
			.then(response => {
        		return response.json();
      		})
			.then(rs => {

                if(rs.success){
                    const data = rs.data;
                    this.setLogined(data.id,data.nickname);
                    alert(this.isLogin());
                }
			});
    }
}

const rq = new Rq();

export default rq;