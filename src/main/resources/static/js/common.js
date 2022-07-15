function s2ab(s) {
    var buf = new ArrayBuffer(s.length); //convert s to arrayBuffer
    var view = new Uint8Array(buf);  //create uint8array as viewer
    for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF; //convert to octet
    return buf;
}

function changeColName(ws, columnNameArray) {

    var range = XLSX.utils.decode_range(ws['!ref']);
    for (var C = range.s.c; C < columnNameArray.length; ++C) {
        var address = XLSX.utils.encode_col(C) + "1"; // <-- first row, column number C
        if (!ws[address]) continue;
        ws[address].v = columnNameArray[C]
    }
}

/**
 * @param title {string} - 엑셀제목
 * @param dataJsonArray {Array.<Object>}- jsonArray 데이터
 * @param orderedColumnArray {Array.<{NAME:string, CODE:string}>} - 재정렬한 COLUMN CODE & NAME
 */
function downloadExcel(title, dataJsonArray, orderedColumnArray) {

    var columnCodeArray = [];
    var columnNameArray = [];
    orderedColumnArray.forEach(function (orderedColumn) {
        columnCodeArray.push(orderedColumn.CODE);
        columnNameArray.push(orderedColumn.NAME);
    });

    var wb = XLSX.utils.book_new();
    var arrJSON = JSON.parse(JSON.stringify(dataJsonArray));
    var dataJsonKeyLength = dataJsonArray.length > 0 && Object.keys(dataJsonArray[0]).length;
    var returnColumnCount = columnNameArray.length;

    //열순서 및 시트화
    var ws = XLSX.utils.json_to_sheet(arrJSON, {header: columnCodeArray});


    //엑셀파일정보
    wb.Props = {
        Title: title,
        Subject: "Excel",
        Author: "Master",
        CreatedDate: new Date()
    };
    //엑셀 첫번째 시트네임
    wb.SheetNames.push(title);

    //열이름변경
    changeColName(ws, columnNameArray);

    //시트에 데이터를 연결
    wb.Sheets[title] = ws;

    //다운로드
    saveAs(new Blob([
        s2ab(XLSX.write(wb, {
            bookType: 'xlsx',
            type: 'binary'
        }))
    ], {
        type: "application/octet-stream"
    }), title + '.xlsx');

}

function jsonParser(orgJson, infoJson) {

    var newJson = [];
    orgJson.forEach(function (data) {

        var obj = {};
        infoJson.forEach(function (info) {
            var code = info.CODE
            var set = info.SET
            if (set == null) {
                obj[code] = data[code];
            } else {
                obj[code] = data[set][0][code]
            }
        })
        newJson.push(obj)
    })

    return newJson

}