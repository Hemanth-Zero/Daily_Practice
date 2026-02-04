function createTable(){
    
    const tabled = document.getElementById("table")
        for(let i = 1 ; i < 8 ; i++){
            let row = tabled.insertRow();
            row.insertCell(0).textContent = "hour"+i
            if(i === 4){
                let cell = row.insertCell(1)
                cell.colSpan = 5
                cell.textContent = " Lunch Break "
                
            }else{
                for(let j = 1 ; j < 6 ; j++){
                    let cell = row.insertCell(j)
                    cell.id = (i*10)+j;
                    
                }
            }
            
        }
}

createTable()

const addB = document.getElementById("Add")
const removeB = document.getElementById("Remove")


function adding(){
    const subject = document.getElementById("Subject").value;
    const hour = document.getElementById("Hour").value;
    const day = document.getElementById("Day").value;
    let id = hour+day
    let cell = document.getElementById(id)
    cell.innerHTML = subject
}

function removing(){
    const hour = document.getElementById("Hour").value;
    const day = document.getElementById("Day").value;
    let id = hour+day
    let cell = document.getElementById(id)
    cell.innerHTML = ""
}
addB.addEventListener("click",adding)
removeB.addEventListener("click",removing)