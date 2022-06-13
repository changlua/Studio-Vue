<template>
  <div>
    <el-card class="box-card">
      <Live2d/>
      <div class="grid-content bg-purple">
        <!--    :rules="rules"    -->
        <el-form ref="elForm" :model="formData" size="medium" label-width="104px">
          <el-col :span="9">
            <el-form-item label="姓名" prop="realName">
              <el-input v-model="formData.realName" placeholder="请输入真实姓名" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-row :gutter="15">
              <el-col :span="9">
                <el-form-item label="专业" prop="majorId">
                  <el-select v-model="formData.majorId" placeholder="请选择专业" filterable clearable
                             :style="{width: '100%'}">
                    <el-option v-for="(item, index) in majorIdOptions" :key="index" :label="item.majorName"
                               :value="item.majorId" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="24">
            <el-row :gutter="15">
              <el-col :span="9">
                <el-form-item label="年级" prop="gradeId">
                  <el-select v-model="formData.gradeId" placeholder="请选择年级" filterable clearable
                             :style="{width: '100%'}">
                    <el-option v-for="(item, index) in gradeIdOptions" :key="index" :label="item.gradeNum"
                               :value="item.gradeId" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="14">
            <el-form-item label="个人介绍" prop="description">
              <el-input v-model="formData.description" type="textarea"
                        placeholder="简单描述下自己就行噢，可以写上自己的目标，想成为的人都可以，50-60主要用于工作室官网展示" :maxlength="60" show-word-limit
                        :autosize="{minRows: 4, maxRows: 4}" :style="{width: '65%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="个人照片" prop="perImg">
              <el-upload
                ref="perImg"
                :file-list="perImgfileList"
                :action="uploadConfig.perImgAction"
                :headers="uploadConfig.headers"
                :before-upload="perImgBeforeUpload"
                :on-success="handleSuccess"
                :on-remove="handleRemove"
                list-type="picture-card"
                accept="image/*"
                :limit="1"
              >
                <el-image v-if="formData.perImg"
                  style="width: 150px; height: 150px"
                  :src="formData.perImg"
                 >
                </el-image>
<!--                <img v-if="formData.perImg" :src="formData.perImg" class="avatar uploadImg" >-->
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item size="large">
              <el-button type="primary" @click="submitForm" v-hasPermi="['own:info:edit']">提交</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import infoApi from "@/api/own/info";
import commonConfig from "@/api/common.js";
import { getToken } from '@/utils/auth'
import { isEmpty } from '@/utils/webtool'
import { removeFile } from "@/api/login";

export default {
  name: "Info",
  components: {},
  props: [],
  data() {
    return {
      formData: {
        realName: undefined,
        majorId: undefined,
        gradeId: undefined,
        description: undefined,
        perImg: null,
      },
      rules: {
        realName: [{
          // required: true,
          message: '请输入真实姓名',
          // trigger: 'blur'
        }],
        majorId: [{
          // required: true,
          message: '请选择专业',
          // trigger: 'change'
        }],
        gradeId: [{
          // required: true,
          message: '请选择年级',
          // trigger: 'change'
        }],
        // description: [{
        //   required: true,
        //   message: '简单描述下自己就行噢，可以写上自己的目标，想成为的人都可以，50-60主要用于工作室官网展示',
        //   trigger: 'blur'
        // }],
        perImg: [{
          // required: true,
          message: '请提交图片',
          // trigger: 'blur'
        }],
      },
      perImgfileList: [],
      //[{},{}]格式
      majorIdOptions: [],
      gradeIdOptions: [],
      //上传配置
      uploadConfig: {
        "perImgAction": '',
        "headers": ''
      },
    }
  },
  computed: {},
  watch: {},
  created() {
    //获取菜单信息：专业、年级
    this.getMenu()
    //获取用户信息
    this.getUserInfo()
    console.log("commonConfig=>", commonConfig)
    //上传配置
    this.uploadConfig = {
      "perImgAction": commonConfig.uploadAction,
      "headers": {
        'Authorization': 'Bearer ' + getToken()
      }
    }
    // console.log("this.uploadConfig=>",this.uploadConfig)
  },
  mounted() {},
  methods: {
    submitForm() {
      // console.log(this.formData)
      // this.$refs['elForm'].validate(valid => {
      //   if (!valid) return
        //提交信息
        infoApi.commitUserInfo(this.formData).then(data=>{
          console.log(data)
          if (data.code == 200) {
            //调用插件方法
            this.$modal.msgSuccess("更新成功！")
          }
        }).catch(err=>console.log(err))
      // })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    perImgBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 10
      if (!isRightSize) {
        this.$message.error('文件大小超过 10MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }
      return isRightSize && isAccept
    },
    getMenu(){
      infoApi.getMenu().then(data => {
        // console.log("getMenus:",data)
        let results = data.data
        this.majorIdOptions = results.majors
        this.gradeIdOptions = results.grades
        this.gradeIdOptions.forEach((grade)=>grade.gradeNum = grade.gradeNum + "级")
      }).catch(err=>console.log(err))
    },
    getUserInfo(){
      infoApi.getUserInfo().then(data => {
        // console.log("getUserInfo:",data)
        let results = data.data
        //需要注意其中的marjorId、gradeId必须转为数字才会对应显示，本身得到的结果时字符串
        //''是防止用户初始登录时单选框为NaN情况
        const majorId = parseInt(results.majorId) ? parseInt(results.majorId): ''
        const gradeId = parseInt(results.gradeId) ? parseInt(results.gradeId): ''
        this.formData = {
            realName: results.realName,
            majorId: majorId,
            gradeId: gradeId,
            description: results.description,
            perImg: results.perImg
        }
        if (!isEmpty(results.perImg)){
          this.perImgfileList.push({
            name: results.perImg.substring(results.perImg.lastIndexOf('/') + 1),
            url: results.perImg
          })
        }
      }).catch(err=>console.log(err))
    },
    //上传成功接口
    handleSuccess (response, file) {
      console.log(response)  //响应的结果
      console.log(file)  //上传的文件信息
      if (response.code === 200) {
        // console.log('上传成功图片地址：' + response.result)
        this.formData.perImg = response.data.visitUrl
        this.$message({
          showClose: true,
          message: '上传成功',
          type: 'success'
        })
      } else {
        this.$message({
          showClose: true,
          message: '上传失败',
          type: 'error'
        })
      }
    },
    // 删除图片
    handleRemove(file, fileList) {
      // 删除图片资源
      const removeFileName = this.formData.perImg.substring(this.formData.perImg.lastIndexOf('/') + 1)
      fileList = []
      this.formData.perImg = ''
      if (!isEmpty(removeFileName)) {
        // 删除图片请求
        removeFile(removeFileName).then((response)=>{
          // console.log('response=>', response)
        }).catch(err=>console.log(err))
      }
      // 更新下用户信息
      infoApi.commitUserInfo(this.formData).then(data=>{
      }).catch(err=>console.log(err))
    },
  }
}

</script>
<style scoped>
.el-upload__tip {
  line-height: 1.2;
}
.box-card {
  /*width: 480px;*/
  border-radius: 7px;
  margin: 16px;
  width: 79%;
  padding: 45px 10px 10px 10px;
}
.uploadImg{
  max-width: 200px
}
</style>
