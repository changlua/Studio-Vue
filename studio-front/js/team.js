var json;

//方式一：每生成一个年级插入到dom元素
function leaveToDom(dom) {
     $("#team .container").append(dom);
}

function generateDom(json){
     //遍历三个节点
     for (const item of json.data.values()) {
          let childDom = '<div class="grade">';
          const grade = item['grade'];
          childDom += `
               <div class="flag wow fadeInUp">
                    <span class="flag_name">${grade}</span>
               </div>
          `;
          const members = item['members'];
          let rowStr = `
               <div class="row">
          `;
          for (const member of members) {
               rowStr += `
               <div class="col-lg-3 col-md-6 wow fadeInUp">
                    <div class="memberbox">
                         <div class="imginfobox">
                              <div class="imgbox">
                                   <img src="./img/members/mjj.jpg" alt="">
                              </div>
                              <div class="infobox">
                                   <p>${member.realName}</p>
                                   <p>身份：${member.roleName}</p>
                                   <p>专业：${member.majorName}</p>
                              </div>
                         </div>
                         <div class="descbox">
                              <div class="spanbox">
                                   ${member.description}
                              </div>
                         </div>
                    </div>
               </div>
               `;
          }
          rowStr += `</div>`;
          childDom += rowStr;
          childDom += `</div>`;
          //生成一个年级就直接挂载
          leaveToDom(childDom)
     }
}

////方式二：每生成一个成员插入到dom元素（最优）
function generateDom2(json){
     console.log(json)
     const teamContainerDom = $("#team .container");
     //遍历三个节点
     for (const [index, item] of json.entries()) {
          let gradeDom = `
               <div class="grade">
               </div>
          `;
          teamContainerDom.append(gradeDom)
          gradeDom = $('.grade').eq(index + 1);//找到指定节点
          const grade = item['grade'];
          const flagDom = `
               <div class="flag wow fadeInUp">
                    <span class="flag_name">${grade}</span>
               </div>
          `;
          gradeDom.append(flagDom)
          const members = item['members'];
          let rowDom = `
               <div class="row">
               </div>
          `;
          gradeDom.append(rowDom)
          rowDom = $('.grade .row').eq(index + 1)
          console.log("rowDom=>",rowDom)
          for (const member of members) {
               // <img src="./img/members/mjj.jpg" alt="">
               const memberDom = `
               <div class="col-lg-3 col-md-6 wow fadeInUp">
                    <div class="memberbox">
                         <div class="imginfobox">
                              <div class="imgbox">
                                   <img src="${member.perImg}" alt="">
                              </div>
                              <div class="infobox">
                                   <p>${member.realName}</p>
                                   <p>身份：${member.roleName}</p>
                                   <p>专业：${member.majorName}</p>
                              </div>
                         </div>
                         <div class="descbox">
                              <div class="spanbox">
                                   ${member.description}
                              </div>
                         </div>
                    </div>
               </div>
               `;
               rowDom.append(memberDom)
          }
     }
}

//服务器地址
//const url = "http://studio.nbdetect.cn/api/common/members"
//本机地址
// const url = "http://127.0.0.1:8999/api/common/members"
//本地虚拟机centos地址：
const url = "http://192.168.3.83/api/common/members"
//个人开源服务器地址
//const url = "http://xxx.cn/api/common/members"

window.onload = function () {
     // var url = "./data/data2.json"/*json文件url，本地的就写本地的位置，如果是服务器的就写服务器的路径*/
     var request = new XMLHttpRequest();
     request.open("get", url);/*设置请求方法与路径*/
     request.send(null);/*不发送数据到服务器*/
     request.onload = function () {/*XHR对象获取到返回信息后执行*/
          if (request.status == 200) {/*返回状态为200，即为数据获取成功*/
               console.log("request=>", request.responseText)
               json = JSON.parse(request.responseText);
               console.log(json);
               // generateDom(json);
               // generateDom2(json.data);
               generateDom2(json.data);
          }
     }
}

