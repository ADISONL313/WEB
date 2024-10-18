const  readExcel=(file)=> {
    return new Promise(resolve=>{
        const reader=new FileReader()
        reader.readAsArrayBuffer(file)
        reader.onload=(ev)=>{
            resolve(ev.target.result)
        }
    })
}  
  
export { readExcel };