<%@page import="species.utils.Utils"%>
<%@ page import="species.dataset.DataTable"%>
<%@ page import="species.dataset.DataPackage"%>
<%@ page import="species.participation.Checklists"%>
<html>
    <head>
        <g:set var="title" value="${g.message(code:'default.dataTable.label')}"/>
        <g:render template="/common/titleTemplate" model="['title':title]"/>
        <style>
            .btn-group.open .dropdown-menu {
            top: 43px;
            }

            .btn-large .caret {
            margin-top: 13px;
            position: absolute;
            right: 10px;
            }

            .btn-group .btn-large.dropdown-toggle {
            width: 300px;
            height: 44px;
            text-align: left;
            padding: 5px;
            }

            .textbox input {
            text-align: left;
            width: 290px;
            height: 34px;
            padding: 5px;
            }

            .form-horizontal .control-label {
            padding-top: 15px;
            }

            .block {
            border-radius: 5px;
            background-color: #a6dfc8;
            margin: 3px;
            }

            .block label {
            float: left;
            text-align: left;
            padding: 10px;
            width: auto;
            }

            .block small {
            color: #444444;
            }

            .left-indent {
            margin-left: 100px;
            }

            span.cke_skin_kama {
                border : 1px solid #D3D3D3 !important;
            }
            .cke_skin_kama .cke_editor {
                display: table !important;
            }

            input.dms_field {
            width: 19%;
            display: none;
            }

            .userOrEmail-list {
            clear:none;
            }
            /*#cke_description {
            width: 100%;
            min-width: 100%;
            max-width: 100%;
            }*/
            .section {
                border:solid 1px lightgrey;
            }
            select {
                height:46px;
            }
            .upload_file div {
                display:inline-block;
            }
            .mapColumns.divider {
                height:20px;
            }
        </style>
    </head>
    <body>
        <div class="row-fluid namelist-wrapper observation_create">
            <div class="span12">
                <uGroup:showSubmenuTemplate  model="['entityName':entityName]"/>

                   <div class="super-section">
                        <div class="section">
                            <h3>Add Datatables</h3>
                            <div id="workspace" style="overflow:auto;background-color:white;border:solid 1px;">
                                <%def allowedDataTableTypes = params.action=='edit' ? [dataTableInstance.dataTableType] : dataTableInstance.dataset.dataPackage.allowedDataTableTypes();%>
                                <div id="allowedDataTableTypes" class="span2" style="margin-left:0px;">
                                    <g:render template="/dataTable/selectDataTable" model="[dataTableTypes : allowedDataTableTypes, datasetInstanceId:dataTableInstance.dataset?.id, dataTableInstance:dataTableInstance]"/>
                                </div>
                                <div id="addDataTable" class="span10">
                                </div>
                            </div>
                        </div>
                   </div>
            </div>

        </div>

        <script type='text/javascript'>
            CKEDITOR.plugins.addExternal( 'confighelper', "${assetPath(src:'ckeditor/confighelper/plugin.js')}" );

            var config = { extraPlugins: 'confighelper', toolbar:'EditorToolbar', toolbar_EditorToolbar:[[ 'Bold', 'Italic' ]]};
        </script>


    <asset:script>

        $(document).ready(function() {	
            <g:if test="${params.action=='edit' || allowedDataTableTypes.size() == 1}">
                $('#allowedDataTableTypes button').first().trigger('click');
            </g:if>
        });
    </asset:script>

</body>

</html>