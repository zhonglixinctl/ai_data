<template>
  <div id="userLayout" :class="['user-layout-wrapper', device]">
    <div class="top" style="width:100%;height:60px;background:#fff;line-height:60px;padding:0 2%;">
      <img src="~@/assets/hylogo.png" alt="" height="40px">
    </div>
    <div class="container" style="display:flex;flex-direction:column;align-items:center;">
      <img src="~@/assets/zi.png" alt="" width="60%">
      <img src="~@/assets/gut.png" alt="" width="15%" style="position:absolute;bottom:9%;cursor:pointer;" @click="getlogin">
      <!-- <div class="top">
        <div class="header">
          <a href="/">
            <img src="~@/assets/logo.svg" class="logo" alt="logo">
            <span class="title">Jeecg Boot</span>
          </a>
        </div>
        <div class="desc">
          Jeecg Boot 是中国最具影响力的 企业级 快速开发平台
        </div>
      </div> -->

      <!-- <route-view></route-view> -->

      <!-- <div class="footer">
        <div class="links"> -->
<!--          <a href="http://doc.jeecg.com" target="_blank">帮助</a>-->
<!--          <a href="https://github.com/zhangdaiscott/jeecg-boot" target="_blank">隐私</a>-->
<!--          <a href="https://github.com/zhangdaiscott/jeecg-boot/blob/master/LICENSE" target="_blank">条款</a>-->
        <!-- </div>
        <div class="copyright">
          Copyright &copy; 2021 <a  target="_blank">北京众立信</a> 出品
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
  import RouteView from "@/components/layouts/RouteView"
  import { mixinDevice } from '@/utils/mixin.js'
  import { mapActions } from "vuex"

  export default {
    name: "UserLayout",
    components: { RouteView },
    mixins: [mixinDevice],
    data () {
      return {}
    },
    mounted () {
      document.body.classList.add('userLayout')
    },
    methods: {
      ...mapActions(['Login', 'Logout', 'PhoneLogin']),
      getlogin(){
        this.Login({username:'zhy',password:'zhy@2021'}).then((res) => {
          console.log('denglu')
          this.$router.replace('/ai')
                // this.$refs.loginSelect.show(res.result)
              }).catch((err) => {
                that.requestFailed(err);
              });
      }
    },
    beforeDestroy () {
      document.body.classList.remove('userLayout')
    },
  }
</script>

<style lang="less" scoped>
body{
  overflow: hidden!important;
}
  #userLayout.user-layout-wrapper {
    min-width:1200px;
    height: 100%;
    display:flex;
    flex-direction: column;
    &.mobile {
      .container {
        .main {
          max-width: 368px;
          width: 98%;
        }
      }
    }

    .container {
      width: 100%;
      flex:1;
      // min-height: 100%;
      background: #f0f2f5 url(~@/assets/bg.png) no-repeat 100%;
      background-size: 100% 100%;
      padding: 110px 0 144px;
      position: relative;

      a {
        text-decoration: none;
      }

      .top {
        text-align: center;

        .header {
          height: 44px;
          line-height: 44px;

          .badge {
            position: absolute;
            display: inline-block;
            line-height: 1;
            vertical-align: middle;
            margin-left: -12px;
            margin-top: -10px;
            opacity: 0.8;
          }

          .logo {
            height: 44px;
            vertical-align: top;
            margin-right: 16px;
            border-style: none;
          }

          .title {
            font-size: 33px;
            color: rgba(0, 0, 0, .85);
            font-family: "Chinese Quote", -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            font-weight: 600;
            position: relative;
            top: 2px;
          }
        }
        .desc {
          font-size: 14px;
          color: rgba(0, 0, 0, 0.45);
          margin-top: 12px;
          margin-bottom: 40px;
        }
      }

      .main {
        min-width: 260px;
        width: 368px;
        margin: 0 auto;
      }

      .footer {
        position: absolute;
        width: 100%;
        bottom: 0;
        padding: 0 16px;
        margin: 48px 0 24px;
        text-align: center;

        .links {
          margin-bottom: 8px;
          font-size: 14px;
          a {
            color: rgba(0, 0, 0, 0.45);
            transition: all 0.3s;
            &:not(:last-child) {
              margin-right: 40px;
            }
          }
        }
        .copyright {
          color: rgba(0, 0, 0, 0.45);
          font-size: 14px;
        }
      }
    }
  }
</style>