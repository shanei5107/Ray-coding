layui.use(['table','jquery','form','fast'], function(){
    var table = layui.table,
        form = layui.form,
        $ = layui.jquery,
        fast = layui.fast;

    table.render({
        elem: '#fieldTable'
        ,url: fast.ctxPath + '/gen/getFields'
        ,page: false
        ,where: {
            tableName: fast.getUrlParam('tableName')
        }
        ,cellMinWidth: 100
        ,cols: [ [
            {field: 'fieldName', title: '字段名称'}
            ,{field: 'dataType', title: 'SQL类型'}
            ,{field: 'columnType', title: '类型长度'}
            ,{field: 'fieldComment', title: '字段描述'}
        ] ]
    });

    // 取消 关闭弹窗
    $('#closeDialog').on('click',function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

});