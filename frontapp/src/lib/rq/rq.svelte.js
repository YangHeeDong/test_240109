// @ts-ignore
import { writable } from 'svelte/store';

class Rq {
    member ={id:0,nickname:""};

    constructor() {
        this.member ={id:0,nickname:""}
    }

    getMember() {
        
        return this.member;
    }

    // @ts-ignore
    setLogined(id, nickname) {
        this.member ={id:id,nickname:nickname};
    }
    
    setLogout() {

        this.member ={id:0,nickname:""};

        fetch("http://localhost:8080/api/v1/member/logout",{
				method : "GET",
				credentials:'include'
			})
			.then(response => {
        		return response.json();
      		})
			.then(rs => {

                if(rs.success){
                    alert("로그아웃 되었습니다.");
                }
			});

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
                }
			});
    }
}

const rq = new Rq();

export default rq;