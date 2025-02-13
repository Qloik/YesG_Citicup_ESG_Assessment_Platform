<template>
    <div>
        <section class="hero">
            <header id="header">
                <img src="../assets/logo_white.png" alt="" style="width:200px">
                <nav>
                    <a id="menu-icon">&#8801;</a>
                </nav>
            </header>
            <header class="hero-header">
                <h1 class="hero-title">ESG Concept Promotes High-quality Development</h1>
            </header>
            <footer class="hero-footer">
                <a class="button button-primary" href="/company">ESG SUMMARY</a>
                <a class="button" href="/compare">ESG COMPARISION</a>
            </footer>
        </section>

        <div style="display: flex;justify-content: center;padding: 5%;padding-top: 10%;">
            <el-card class="box-card" style="margin-right: 5px;">
                <div>
                    <span style="font-size: 25px;font-weight: bold;">News</span>
                </div>
                <div style="margin-top: 5px;padding-top: 2%;" @click="jumpNews">
                    <img src="../assets/news.jpg" alt="" style="width:100%; height:280px;object-fit: cover;" >
                </div>
            </el-card>
            <el-card class="box-card" style="margin-right: 5px;" @click="jumpGreen">
                <div>
                    <span style="font-size: 25px;font-weight: bold;">Investment Briefing</span>
                </div>
                <div style="margin-top: 5px;padding-top: 2%;">
                    <img src="../assets/investment.jpg" alt="" style="width:100%; height:280px;object-fit: cover;" >
                </div>
            </el-card>
            <el-card class="box-card" @click="jumpPdf">
                <div>
                    <span style="font-size: 25px;font-weight: bold;">Corporate Report</span>
                </div>
                <div style="margin-top: 5px;padding-top: 2%;">
                    <img src="../assets/reports.jpg" alt="" style="width:100%; height:280px;object-fit: cover;" >
                </div>
            </el-card>
        </div>
        
        
        <article style="max-width:100%;">
            <div class="title" style="text-align:left; background: url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) center;padding: 3%;color: white;">Multi Source Data
                <div style="padding-top:20px; max-width:60%;font-size:30px; text-align:left; color: white;">Data sources include news, corporate annual reports, ten rating agencies, etc</div>      
            </div>  

            <div class="title" style="text-align:right;">Multi Industry Comparison
                <div style="padding-top:20px;padding-left:40% ; max-width:60%;font-size:30px; text-align:right; color: grey;">Select different indicators and characteristics according to different industries for ESG evaluation</div>      
            </div>    

            <div class="title" style="text-align:left; background: url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) bottom; padding: 3%;color: white;">Comprehensive Evaluation of ESG
                <div style="padding-top:20px; max-width:50%;font-size:30px; text-align:left; color: white;">Score based on third-party data, topic quality, and topic breadth</div>      
            </div> 
        </article>

        
        <!-- 底栏 -->
        <div style="position: static;background-color:#02040d;height: 75px;position: relative;">
            <div style="color:white;display: flex;align-items: center;justify-content:center;height: 100%;">
                <p>2022-2023第十八届“花旗杯”金融创新应用大赛参赛作品</p>
            </div>
        </div>
    </div>
</template>

<script>
export default 
{
    data(){
        return{
        }
    },
    methods:{
        jumpNews(){
            this.$router.push('/news')
        },
        jumpGreen(){
            // this.$router.push('/')
            window.open("https://weibo.com/ttarticle/p/show?id=2309404854254522466425","_blank");
        },
        jumpPdf(){
            // this.$router.push('/')
            window.open("https://www1.hkexnews.hk/search/titlesearch.xhtml?lang=zh","_blank");
        },
    }
    
}
</script>

<style scoped>
body {
    font-family: 'PT Sans Caption', "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
    font-size: 3vh;
}

#header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

#logo {
    text-decoration: none;
    color: white;
    font-size: 5vh;
    font-weight: bold;
}

#menu-icon {
    font-family: Geneva, sans-serif;
    font-size: 6vh;
}

.hero {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 100vh;
    padding: 5%;
    box-sizing: border-box;
    color: white;
    background: url(https://images.unsplash.com/photo-1500417148159-68083bd7333a) center center no-repeat;
    background-size: cover;
}

.hero-title {
    max-width: 14em;
    margin: 0;
    font-size: 12vh;
    font-weight: bold;
    line-height: 1.3;
}

.title {
    font-size: 8vh;
    font-weight: bold;
    line-height: 1.3;
    margin-right: 0;
    margin-bottom: 150px;
}

@media only screen and (min-width: 32em) {
    .hero-title {
        font-size: 12vh;
    }
}

.hero-footer {
    display: flex;
    margin-bottom: 1em;
}

.button {
    padding: .5em .67em;
    color: white;
    text-decoration: none;
    border: .1em solid white;

}

.button-primary {
    color: black;
    background-color: white;
}


article {
    max-width: 36em;
    margin: 0 auto;
    padding: 10%;
}

.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}


.box-card {
    height: 370px;
    width: 500px;
    margin: 20px 10px;
}

.card-long{
    width:100%;
    margin: 5%;
}
</style>