function indexOf(arr,item) {
    var index;
    for(var i = 0;i<arr.length;i++){
        if(arr[i] === item){
            index = i;
        }
    }
    return index;
}

function sum(arr) {
    var sum = 0;
    for(var i = 0;i < arr.length;i++){
        sum += arr[i];
    }
    return sum;
}

function remove(arr,index) {
    var newArr = new Array();
    var index = 0;
    for(var i = 0;i < arr.length;i++) {
        if(i === index){
            continue;
        }else{
            newArr[index] = arr[i];
            index++;
        }
    }
    console.log(newArr);
}

function append(arr,item) {
    arr[arr.length] = item;
    console.log(arr);
}

function truncate(arr) {
    var newArr = new Array();
    for(var i = 0;i < arr.length-1;i++) {
        newArr[i] = arr[i];
    }
    console.log(newArr);
}

function curtail(arr) {
    var newArr = new Array();
    var index = 0
    for(var i = 1;i < arr.length;i++) {
        newArr[index] = arr[i];
        index++;
    }
    console.log(newArr);
}

function concat(arr1,arr2) {
    var newArr = new Array();
    for(var i = 0;i < arr1.length;i++) {
        newArr[i] = arr1[i];
    }
    var index = 0;
    for(var i = arr1.length; i < arr1.length + arr2.length;i++) {
        newArr[i] = arr2[index];
        index++;
    }
    console.log(newArr);
}

function insert(arr,item,index) {
    var i=arr.length-1;
    while(i>index-1){
        arr[i+1] = arr[i];
        i--;
    }
    arr[i] = item;
    console.log(arr);
}

function count(arr,value) {
    var count = 0;
    for(var i = 0;i<arr.length;i++){
        if(arr[i] === value){
            count++;
        }
    }
    return count;
}

function  duplicates(arr) {
    var index = 0;
    var countArry = new Array();
    for(var i = 0;i<arr.length;i++){
        for(var j = i;j < arr.length;i++)
        if(arr[i] == arr[j]){
            countArry[index] = arr[i];
            index++;
        }
    }
    console.log(countArry);
}

function square(arr) {
    for(var i = 0;i < arr.length; i++){
        arr[i] = arr[i]*arr[i];
    }
    console.log(arr);
}

function findAllOccurrences(arr, target) {
    var indexArry = new Array();
    var index = 0;
    for(var i = 0;i < arr.length; i++){
        if(arr[i] === target){
            indexArry[index] = i;
            index++;
        }
    }
    console.log(indexArry);
}