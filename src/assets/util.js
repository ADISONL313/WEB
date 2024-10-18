import * as XLSX from 'xlsx'
import axios from 'axios'
const character={
    studentName:{
        text:"姓名",
        type:"string"
    },
    college:{
        text:"学院",
        type:"string"
    },
    major:{
        text:"专业",
        type:"number"
    },
    id_card:{
        text:"身份证",
        type:"string"
    },
    counselor:{
        text:"辅导员账号",
        type:"string"
    },
    mobile:{
        text:"电话",
        type:"string"
    },
    gender:{
        text:"性别",
        type:"string"
    }
}

const  readExcel=(file)=> {
    return new Promise(resolve=>{
        const reader=new FileReader()
        reader.readAsArrayBuffer(file)
        reader.onload=(ev)=>{
            resolve(ev.target.result)
        }
    })
}  


const exportToExcel=()=> {  
    // 示例JSON数据  
    const jsonData = [  
      { 姓名: "小红", 学院: "人工智能与大数据学院", 专业: "软件工程",身份证:"11111111111111111X",辅导员账号:"xxx",电话:"11111111111",性别:"男" },  
      { 姓名: "小黑", 学院: "人工智能与大数据学院", 专业: "软件工程",身份证:"11111111111111111X",辅导员账号:"xxx",电话:"11111111111",性别:"男" }
    ];  

    // 将JSON数据转换为工作表  
    const ws = XLSX.utils.json_to_sheet(jsonData);  

    // 创建一个新的工作簿，并将工作表添加到工作簿中  
    const wb = XLSX.utils.book_new();  
    XLSX.utils.book_append_sheet(wb, ws, "Sheet1");  

    // 生成Excel文件（二进制字符串）  
    const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });  

    // 创建一个Blob对象  
    const blob = new Blob([s2ab(wbout)], { type: "application/octet-stream" });  

    // 创建一个指向文件的URL  
    const url = URL.createObjectURL(blob);  

    // 创建一个临时的a标签用于下载  
    const link = document.createElement('a');  
    link.href = url;  
    link.download = '学生信息表格模板.xlsx'; // 指定下载的文件名  
    document.body.appendChild(link);  
    link.click();  

    // 清理  
    document.body.removeChild(link);  
    URL.revokeObjectURL(url);  
  }
  // 辅助函数：将二进制字符串转换为ArrayBuffer  
  const s2ab=(s)=> {  
    const buf = new ArrayBuffer(s.length);  
    const view = new Uint8Array(buf);  
    for (let i = 0; i < s.length; i++) {  
      view[i] = s.charCodeAt(i) & 0xFF;  
    }  
    return buf;  
  }
  const ChinaProvince=[  
    {  
        "id": 1,  
        "label": "黑龙江省",  
        "value": "黑龙江省"  
    },  
    {  
        "id": 2,  
        "label": "吉林省",  
        "value": "吉林省"  
    },  
    {  
        "id": 3,  
        "label": "辽宁省",  
        "value": "辽宁省"  
    },  
    {  
        "id": 4,  
        "label": "河北省",  
        "value": "河北省"  
    },  
    {  
        "id": 5,  
        "label": "山西省",  
        "value": "山西省"  
    },  
    {  
        "id": 6,  
        "label": "青海省",  
        "value": "青海省"  
    },  
    {  
        "id": 7,  
        "label": "山东省",  
        "value": "山东省"  
    },  
    {  
        "id": 8,  
        "label": "河南省",  
        "value": "河南省"  
    },  
    {  
        "id": 9,  
        "label": "江苏省",  
        "value": "江苏省"  
    },  
    {  
        "id": 10,  
        "label": "安徽省",  
        "value": "安徽省"  
    },  
    {  
        "id": 11,  
        "label": "浙江省",  
        "value": "浙江省"  
    },  
    {  
        "id": 12,  
        "label": "福建省",  
        "value": "福建省"  
    },  
    {  
        "id": 13,  
        "label": "江西省",  
        "value": "江西省"  
    },  
    {  
        "id": 14,  
        "label": "湖南省",  
        "value": "湖南省"  
    },  
    {  
        "id": 15,  
        "label": "湖北省",  
        "value": "湖北省"  
    },  
    {  
        "id": 16,  
        "label": "广东省",  
        "value": "广东省"  
    },  
    {  
        "id": 17,  
        "label": "台湾省",  
        "value": "台湾省"  
    },  
    {  
        "id": 18,  
        "label": "海南省",  
        "value": "海南省"  
    },  
    {  
        "id": 19,  
        "label": "甘肃省",  
        "value": "甘肃省"  
    },  
    {  
        "id": 20,  
        "label": "陕西省",  
        "value": "陕西省"  
    },  
    {  
        "id": 21,  
        "label": "四川省",  
        "value": "四川省"  
    },  
    {  
        "id": 22,  
        "label": "贵州省",  
        "value": "贵州省"  
    },  
    {  
        "id": 23,  
        "label": "云南省",  
        "value": "云南省"  
    },  
    {  
        "id": 24,  
        "label": "内蒙古自治区",  
        "value": "内蒙古自治区"  
    },  
    {  
        "id": 25,  
        "label": "新疆维吾尔自治区",  
        "value": "新疆维吾尔自治区"  
    },  
    {  
        "id": 26,  
        "label": "西藏自治区",  
        "value": "西藏自治区"  
    },  
    {  
        "id": 27,  
        "label": "宁夏回族自治区",  
        "value": "宁夏回族自治区"  
    },  
    {  
        "id": 28,  
        "label": "广西壮族自治区",  
        "value": "广西壮族自治区"  
    },  
    {  
        "id": 29,  
        "label": "北京市",  
        "value": "北京市"  
    },  
    {  
        "id": 30,  
        "label": "天津市",  
        "value": "天津市"  
    },  
    {  
        "id": 31,  
        "label": "上海市",  
        "value": "上海市"  
    },  
    {  
        "id": 32,  
        "label": "重庆市",  
        "value": "重庆市"  
    },  
    {  
        "id": 33,  
        "label": "香港特别行政区",  
        "value": "香港特别行政区"  
    },  
    {  
        "id": 34,  
        "label": "澳门特别行政区",  
        "value": "澳门特别行政区"  
    }  
]
const provinceList={  
    北京市: [116.407396, 39.904200],  
    天津市: [117.200983, 39.084158],  
    上海市: [121.473701, 31.230416],  
    重庆市: [106.551556, 29.563009],  
    河北省: [114.539709, 38.035828],  
    山西省: [112.549246, 37.857014],  
    辽宁省: [123.429091, 41.796768],  
    吉林省: [125.324500, 43.886841],  
    黑龙江省: [126.642464, 45.756967], 
    江苏省: [118.778063, 32.061700],  
    浙江省: [120.193832, 30.258133],  
    安徽省: [117.286408, 31.86166],   
    福建省: [119.306236, 26.075301],  
    江西省: [115.892151, 28.676493],  
    山东省: [117.000923, 36.675807],  
    河南省: [113.650290, 34.761496],  
    湖北省: [114.305265, 30.593062],  
    湖南省: [112.982279, 28.194091],  
    广东省: [113.280637, 23.125178],  
    海南省: [110.350227, 20.017222],  
    四川省: [104.066771, 30.651632],  
    贵州省: [106.713468, 26.578349],  
    云南省: [102.712251, 25.040609],  
    陕西省: [108.948024, 34.263161],  
    甘肃省: [103.823556, 36.057031],  
    青海省: [101.778916, 36.621083],  
    台湾省: [121.509062, 25.047766],  
    内蒙古自治区: [111.670801, 40.817193], 
    广西壮族自治区: [108.321941, 22.816668], 
    西藏自治区: [91.132212, 29.665246], 
    宁夏回族自治区: [106.269958, 38.470138], 
    新疆维吾尔自治区: [87.617733, 43.792818], 
    香港特别行政区: [114.179673, 22.319304], 
    澳门特别行政区: [113.549096, 22.198745]  
}
export { readExcel,character,exportToExcel,ChinaProvince,provinceList};
