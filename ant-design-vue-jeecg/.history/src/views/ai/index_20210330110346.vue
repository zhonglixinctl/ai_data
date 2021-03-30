<template>
  <div class="box" style="padding:50px;box-sizing:border-box;">
    <!--        <button  @click="handleExportXls('用户信息')">导出</button>-->
    <div class="up" style="display: flex;">
      <a-upload
        name="file"
        :limit="0"
        :multiple="true"
        action="http://192.168.3.23:8900/jeecg-boot/zhy/importExcel"
        @change="handleChange"
      >
        <a-button type="primary" icon="cloud">数据分析</a-button>
      </a-upload>
      <a-button @click="daochu" type="primary" icon="cloud-download" style="margin-left:100px;">数据导出</a-button>
    </div>
    <div id="data" style="overflow: auto;width: 100%;height:800px;padding: 0">
      <table border="1" style="width: 100%;border-collapse: collapse;overflow: scroll">

        <tr v-for="(item,index) in tble" :key="index">
          <td v-for="(it,ind) in item" :key="ind" style="text-align: left;min-width: 200px;"
              :style="{'color':ind == 1 ? 'red':'' }">
            {{ it }}
          </td>
        </tr>

      </table>
    </div>

  </div>
</template>
<script src="http://cdn.staticfile.org/FileSaver.js/1.3.8/FileSaver.min.js"></script>
<script>
import { postAction, getAction, downFile } from '@api/manage'
// import FileSaver from 'file-saver'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import { axios } from '@/utils/request'

export default {
  name: 'index',
  mixins: [JeecgListMixin],
  data() {
    return {

      tble: [],
      thd: [],
      url: {
        syncUser: '/act/process/extActProcess/doSyncUser',
        list: '/sys/user/list',
        delete: '/sys/user/delete',
        deleteBatch: '/sys/user/deleteBatch',
        exportXlsUrl: '/sys/user/exportXls',
        importExcelUrl: 'sys/user/importExcel'
      }
    }
  },
  components: {
    // FileSaver
  },
  methods: {
    request(a) {
      console.log(a)
      postAction('http://10.0.0.10:8900/jeecg-boot/zhy/importExcel', a.data).then(res => {
        if (res.code == 200) {

        } else {
          this.$message.error(res.message)
        }
      })
    },

    downloadFileByBase64(base64, name) {
      var myBlob = this.dataURLtoBlob(base64)
      var myUrl = URL.createObjectURL(myBlob)
      downloadFile(myUrl, name)
    },
    downloadFile(url, name = 'What\'s the fuvk') {
      var a = document.createElement('a')
      a.setAttribute('href', url)
      a.setAttribute('download', name)
      a.setAttribute('target', '_blank')
      let clickEvent = document.createEvent('MouseEvents')
      clickEvent.initEvent('click', true, true)
      a.dispatchEvent(clickEvent)
    },
    dataURLtoBlob(dataurl) {
      var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new Blob([u8arr], { type: mime })
    },
    daochu() {
      axios({
        url: '/zhy/exportXls',
        // params: parameter,
        method: 'get',
        responseType: 'blob'
      }).then(res => {
        if (!res) {
          this.$message.warning('文件下载失败')
          return
        }

        let filename = 'filename.xls'// 判断是否使用默认文件名
        if (typeof window.navigator.msSaveBlob !== 'undefined') {
          window.navigator.msSaveBlob(blob, filename)
        } else {
          var blobURL = window.URL.createObjectURL(new Blob([res], { type: 'application/vnd.ms-excel' }))// 将blob对象转为一个URL
          var tempLink = document.createElement('a')// 创建一个a标签
          tempLink.style.display = 'none'
          tempLink.href = blobURL
          tempLink.setAttribute('download', filename)// 给a标签添加下载属性

          document.body.appendChild(tempLink)// 将a标签添加到body当中
          tempLink.click()// 启动下载
          document.body.removeChild(tempLink)// 下载完毕删除a标签
          window.URL.revokeObjectURL(blobURL)
        }
      })
    },

    handleChange(info) {
      const status = info.file.status
      if (status !== 'uploading') {
        console.log(info.file)
      }
      if (status === 'done') {
        this.$message.success(`${info.file.name} file uploaded successfully.`)
        console.log(info.file.response.result)
        console.log(info.file.response.result.data[0])

        this.tble = info.file.response.result.data
        console.log(this.thd)
        console.log(this.tble)
      } else if (status === 'error') {
        this.$message.error(`${info.file.name} file upload failed.`)
      }
    }
  }
}
</script>

<style scoped>
.up {
  width: 10%;
  height: 10%;
}

.box {
  width: 100%;
  height: 900px;
  background: url('../../assets/aa.jpeg') no-repeat;
  background-size: ;
  display: inline-block;
}
.box::before{
  content:'';
  filter:blur(5px);
}Z
</style>