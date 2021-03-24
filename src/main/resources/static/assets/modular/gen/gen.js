layui.use(['table','jquery','layer','fast'], function(){
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        fast = layui.fast;

    var checkTables = [];

    var tableIns = table.render({
        elem: '#genTable'
        ,url: fast.ctxPath + '/gen/list'
        ,page: true
        ,cellMinWidth: 100
        ,cols: [ [
            {type: 'checkbox'}
            ,{field:'tableName', title: '表名'}
            ,{field:'dataRows', title: '数据行', sort: true}
            ,{field:'tableDescribe', title: '表描述'}
            ,{field:'createTime', title: '创建时间', sort: true}
            ,{field:'updateTime', title: '修改时间', sort: true}
            ,{fixed: 'right', align: 'center', toolbar: '#tableBar', minWidth: 300, title: '操作'}
        ] ]
    });

    // 搜索
    $('#searchBtn').on('click',function () {
        tableIns.reload({
            where: fast.getFormData('genForm'),
            page: {curr: 1}
        });
    });

    // 重置搜索条件
    $('#resetBtn').on('click',function () {
        document.getElementById('genForm').reset();
        tableIns.reload({
            where: fast.getFormData('genForm'),
            page: {curr: 1}
        });
    });

    // 代码生成
    $('#createBtn').on('click',function () {
        if(checkTables.length < 1){
            layer.alert('请勾选要生成代码的表', {
                icon: 2
            });
            return;
        }
        // 表单数据
        var formData = fast.getFormData('createForm');
        formData.tableNames = checkTables;
        // 包名校验
        if(formData.packageName === null || formData.packageName === undefined || formData.packageName ===''){
            layer.alert('请输入包名', {
                icon: 2
            });
            return;
        }
        if(formData.moduleName === null || formData.moduleName === undefined || formData.moduleName ===''){
            layer.alert('请输入模块名称', {
                icon: 2
            });
            return;
        }
        // 发送请求
        fast.download(fast.ctxPath + '/gen/execute','code.zip', formData);
        return false;
    });

    // 代码生成重置
    $('#createRest').on('click',function () {
        document.getElementById('packageName').value = '';
        document.getElementById('moduleName').value = '';
    });

    // 查看表结构
    function info(data){
        layer.open({
            type: 2,
            title: '表结构',
            content: fast.ctxPath + '/gen/info?tableName=' + data.tableName,
            area: ['80%', '80%'],
            max: true
        });
    };

    // 表格行工具栏事件监听
    table.on('tool(genTable)', function (obj) {
        var data = obj.data
            ,event = obj.event;

        if(event === 'info') {
            info(data);
        }
    });

    table.on("checkbox(genTable)", function (obj) {
        var checkStatus = table.checkStatus('genTable');
        checkTables = [];
        if(checkStatus.data.length > 0){
            for (i = 0; i < checkStatus.data.length; i++) {
                checkTables.push(checkStatus.data[i].tableName);
            }
        }
    });

});