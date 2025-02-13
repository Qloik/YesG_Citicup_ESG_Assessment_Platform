<template>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <div class="body">

        <div class="veen">
            <div class="login-btn splits">
                <!-- <img src="../assets/logo_white.png" alt="" style="height:40px"> -->
                <p style="padding-top:10px">Already an user?</p>
                <button class="active">Login</button>
            </div>
            <div class="rgstr-btn splits">
                <img src="../assets/logo_white.png" alt="" style="height:40px">
                <p style="padding-top:10px">Don't have an account?</p>
                <button>Register</button>
            </div>
            <div class="wrapper">
                <form id="login" tabindex="500" target="rfFrame">
                    <h3 style="font-size:30px;font-weight: bold;">Login</h3>
                    <div class="mail">
                        <input type="tel" name="" pattern="[0-9]{11}" v-model="login.number" required>
                        <label>Phone Number</label>
                    </div>
                    <div class="passwd">
                        <input type="password" v-model="login.pw" name="" required>
                        <label>Password</label>
                    </div>

                    <button class="dark" @click="Login()">Login</button>
                </form>
                <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
                <form id="register" tabindex="502" target="rfFrame">
                    <h3 style="font-size:30px;font-weight: bold;">Register</h3>
                    <div class="name">
                        <input type="text" name="" v-model="register.name" required>
                        <label>User Name</label>
                    </div>
                    <div class="tel">
                        <input type="tel" name="" v-model="register.number" required>
                        <label>Phone Number</label>
                    </div>
                    <div class="passwd">
                        <input type="password" name="" v-model="register.pw1" required>
                        <label>Password</label>
                    </div>
                    <div class="passwd">
                        <input type="password" name="" v-model="register.pw2" required>
                        <label>Repeate Password</label>
                    </div>

                    <!-- <div class="submit"> -->
                    <button class="dark" @click="Register()">Register</button>
                    <!-- </div> -->
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import $ from 'jquery';
window.jQuery = $;
window.$ = $;
import axios from 'axios';

export default {
    inject: ["reload"],
    data() {
        return {
            register: {
                name: '',
                number: '',
                pw1: '',
                pw2: '',
            },
            login: {
                number: '',
                pw: '',
            }

        }
    },
    setup() {
        $(document).ready(function () {
            $(".veen .rgstr-btn button").click(function () {
                $('.veen .wrapper').addClass('move');
                $('.body').css('background', 'url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) center center no-repeat');
                $('.body').css('background-size', 'cover');
                $(".veen .login-btn button").removeClass('active');
                $(this).addClass('active');

            });
            $(".veen .login-btn button").click(function () {
                $('.veen .wrapper').removeClass('move');
                $('.body').css('background', 'url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) center center no-repeat');
                $('.body').css('background-size', 'cover');
                $(".veen .rgstr-btn button").removeClass('active');
                $(this).addClass('active');
            });
        });
        $("#btnSubmit").click(function () {
            $("#form").attr("target", "rfFrame");
        });


    },
    methods:
    {
        /**判断是否是手机号**/
        isPhoneNumber(tel) {
            var reg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            if(!reg.test(tel))
                alert('请输入合法的手机号码')
            return reg.test(tel);
        },
        // 判断两个密码是否一致
        check2pwd() {
            if (this.register.pw1 != this.register.pw2) {
                alert("两次输入密码不一致！")
                this.register.pw1 = "";
                this.register.pw2 = "";
                return false;
            }
            return true
        },
        Register() {
            console.log('regis')
            if (this.register.pw1 != '' && this.register.pw2 != '') {
                console.log('pw ok')
                if (this.check2pwd() == true && this.isPhoneNumber(this.$data.register.number) && this.$data.register.name != '') {
                    axios({
                        method: 'post',
                        url: '/user/register',
                        params: {
                            id: this.$data.register.number,
                            name: this.$data.register.name,
                            password: this.$data.register.pw1
                        }
                    }).then((res) => {
                        console.log('register', res.data)
                        if (res.data.false == 'id repeat')
                            alert("该电话号码已注册")
                        else{
                            alert("注册成功")
                            location. reload()
                            this.$router.go(0)  
                        }
                    }, error => {
                        console.log('错误', error.message)
                    })
                }
            }


        },
        Login() {
            // console.log(this.login)
            if (this.isPhoneNumber(this.$data.login.number) && this.$data.login.password != '') {
                axios({
                    method: 'post',
                    url: '/user/login',
                    params: {
                        id: this.$data.login.number,
                        password: this.$data.login.pw
                    }
                }).then((res) => {
                    console.log('login', res.data)
                    if (res.data.false == 'id not exist')
                        alert('该手机号未注册')
                    else if (res.data.false == 'wrong password') {
                        alert('密码错误')
                        this.$data.login.pw = ''
                    }
                    else {
                        localStorage.setItem("userid", this.$data.login.number)
                        localStorage.setItem("name", res.data.true)
                        this.$router.push('/home')
                        // window.localStorage
                    }
                    // this.$router.push('/login')
                }, error => {
                    console.log('错误', error.message)
                })
            }
        }

    }

}

</script>

<style scoped>
.body {
    background: url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) center center no-repeat;
    background-size: cover;
    transition: all .5s;
    padding: 1px;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}

.veen {
    width: 70%;
    margin: 100px auto;
    background: rgba(255, 255, 255, .5);
    min-height: 400px;
    display: table;
    position: relative;
    box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
}

.veen>div {
    display: table-cell;
    vertical-align: middle;
    text-align: center;
    color: #fff;
}

.veen button {
    background: transparent;
    /* //background-image: linear-gradient(90deg, #e0b722, #ff4931); */
    display: inline-block;
    padding: 10px 30px;
    border: 3px solid #fff;
    border-radius: 50px;
    background-clip: padding-box;
    position: relative;
    color: #FFF;
    /* //box-shadow: 0 0 4px rgba(0,0,0,.14), 0 4px 8px rgba(0,0,0,.28); */
    transition: all .25s;
}

.veen button.dark {
    border-color: black;
    background: black;
}

.veen .move button.dark {
    border-color: black;
    background: black;
}

.veen .splits p {
    font-size: 25px;
}

.veen button:active {
    box-shadow: none;
}

.veen button:focus {
    outline: none;
}

.veen>.wrapper {
    position: absolute;
    width: 40%;
    height: 120%;
    top: -5%;
    left: 5%;
    background: #fff;
    box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
    transition: all .5s;
    color: #303030;
    overflow: hidden;
}

.veen .wrapper>form {
    padding: 15px 30px 30px;
    width: 100%;
    transition: all .5s;
    background: #fff;
    width: 100%;
}

.veen .wrapper>form:focus {
    outline: none;
}

.veen .wrapper #login {
    padding-top: 20%;
    visibility: visible;
}

.veen .wrapper #register {
    transform: translateY(-80%) translateX(100%);
    visibility: hidden;
}

.veen .wrapper.move #register {
    transform: translateY(-80%) translateX(0%);
    visibility: visible;
}

.veen .wrapper.move #login {
    transform: translateX(-100%);
    visibility: hidden;
}

.veen .wrapper>form>div {
    position: relative;
    margin-bottom: 15px;
}

.veen .wrapper label {
    position: absolute;
    top: -7px;
    font-size: 12px;
    white-space: nowrap;
    background: #fff;
    text-align: left;
    left: 15px;
    padding: 0 5px;
    color: #999;
    pointer-events: none;
}

.veen .wrapper h3 {
    margin-bottom: 25px;
}

.veen .wrapper input {
    height: 40px;
    padding: 5px 15px;
    width: 100%;
    border: solid 1px #999;
}

.veen .wrapper input {
    height: 40px;
    padding: 5px 15px;
    width: 100%;
    border: solid 1px #999;
}

.veen .wrapper input:focus {
    outline: none;
    border-color: black;
}

.veen>.wrapper.move {
    left: 45%;
}

.veen>.wrapper.move input:focus {
    border-color: black;
}

@media (max-width: 767px) {
    .veen {
        padding: 5px;
        margin: 0;
        width: 100%;
        display: block;
    }

    .veen>.wrapper {
        position: relative;
        height: auto;
        top: 0;
        left: 0;
        width: 100%;
    }

    .veen>div {
        display: inline-block;
    }

    .splits {
        width: 50%;
        background: #fff;
        float: left;
    }

    .splits button {
        width: 100%;
        border-radius: 0;
        background: #505050;
        border: 0;
        opacity: .7;
    }

    .splits button.active {
        opacity: 1;
    }

    .splits button.active {
        opacity: 1;
        background: black;
    }

    .splits.rgstr-btn button.active {
        background: black;
    }

    .splits p {
        display: none;
    }

    .veen>.wrapper.move {
        left: 0%;
    }
}

input:-webkit-autofill,
textarea:-webkit-autofill,
select:-webkit-autofill {
    box-shadow: inset 0 0 0 50px #fff
}

.site-link {
    padding: 5px 15px;
    position: fixed;
    z-index: 99999;
    background: #fff;
    box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
    right: 30px;
    bottom: 30px;
    border-radius: 10px;
}

.site-link img {
    width: 30px;
    height: 30px;
}
</style>