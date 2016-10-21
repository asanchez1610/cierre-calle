Ext.ns('UsuarioService');

UsuarioService = {

	init:function(){
		this.buildGridUsuario();
	}
	,
	registroUsuario:function(options){
		
		options = options || {};
		
			console.log(options)
		
		Ext.create('Ext.window.Window', {
		    title: options.title,
		    height: 500,
		    width: 600,
		    modal:true,
		    id:'win-usuarios',
		    layout: 'column',
		    bodyStyle:'padding:10px 10px 0px 10px;',
		    items: [
		    		{
		    			xtype:'form',
		    			layout: 'column',
		    			width:580,
		    			id:'form-usuario',
		    			listeners:{
		    			
		    				afterrender:function(){
		    				
		    					var thisWin = Ext.getCmp('win-usuarios');
		    					
		    					thisWin.body.mask('Obteniendo información...');
		    					
		    					function isChecked(opciones,idOpcion){
		    					
		    						var checked = false;
		    						
		    						for(var i = 0 ; i < opciones.length ; i++ ){
		    							
		    							if(opciones[i].idOpcion == idOpcion){
		    								checked = true;
		    								break;
		    							}
		    							
		    						}
		    					
		    						return checked;
		    					}
		    					
		    					Util.runAjax({
										url:PATH_PROYECTO_BASE+'cierre-calles/opciones-list',
										async : true,
										method : 'POST',
										success:function(http){
											var response = Ext.decode(http.responseText);

											// {boxLabel: 'Item 2', name: 'cb-horiz-2', checked: true},
											
											if(options.ideUsuario){
											
												Util.runAjax({
													url:PATH_PROYECTO_BASE+'cierre-calles/usuario-id',
													async : true,
													method : 'POST',
													params:{idUsuario:options.ideUsuario},
													success:function(httpUser){
													
														var responseUser = Ext.decode(httpUser.responseText);
														
														Ext.getCmp('desLoginUser').setValue(responseUser.usuario.desLoginUser);
														Ext.getCmp('clvClaveUser').setValue(responseUser.usuario.clvClaveUser);
														Ext.getCmp('desNombreCompleto').setValue(responseUser.usuario.desNombreCompleto);
														Ext.getCmp('email').setValue(responseUser.usuario.email);
														Ext.getCmp('dni').setValue(responseUser.usuario.dni);
														
														if(responseUser.usuario.desParametro1 == 'S'){
														 Ext.getCmp('desParametro1').checked = true;
														 Ext.getCmp('desParametro1').setRawValue(true); 
														}
														
														Ext.getCmp('form-usuario').add({
																				        xtype: 'hiddenfield',
																				        name: 'ideUsuario',
																				        value: responseUser.usuario.ideUsuario
																				    });
														
														Ext.each(response.data,function(item,i){
															Ext.getCmp('chkGroupOpciones').add({boxLabel: item.nombre, name: 'opcionesTemp['+i+'].idOpcion', inputValue:item.idOpcion,checked : isChecked(responseUser.usuario.opciones,item.idOpcion)});
														});
													
														thisWin.body.unmask();
														
														
													}
												});
												
											}else{
												
												Ext.each(response.data,function(item,i){
													Ext.getCmp('chkGroupOpciones').add({boxLabel: item.nombre, name: 'opciones['+i+'].idOpcion', inputValue:item.idOpcion});
												});
											
												thisWin.body.unmask();
												
											}
											
											
											
										}
									});
		    					
		    				}
		    			
		    			},
		    			items:[
		    					{
		    						xtype:'textfield'
		    						,width:280
		    						,style:'margin:0px 10px 0px 0px'
		    						,fieldLabel:'Usuario'
		    						,labelWidth:70
		    						,name:'desLoginUser'
		    						,id:'desLoginUser'
		    						,allowBlank:false
		    					},
		    					{
		    						xtype:'textfield'
		    						,width:285
		    						,inputType:'password'
		    						,labelStyle:'padding-right:10px;'
		    						,fieldLabel:'Contraseña'
		    						,name:'clvClaveUser'
		    						,id:'clvClaveUser'
		    						,labelWidth:70
		    						,allowBlank:false
		    					},
		    					{
		    						xtype:'textfield'
		    						,width:575
		    						,style:'margin:10px 10px 0px 0px'
		    						,fieldLabel:'Nombres'
		    						,name:'desNombreCompleto'
		    						,id:'desNombreCompleto'
		    						,labelWidth:70
		    						,allowBlank:false
		    					},
		    					{
		    						xtype:'textfield'
		    						,width:575
		    						,style:'margin:10px 10px 0px 0px'
		    						,fieldLabel:'Email'
		    						,name:'email'
		    						,id:'email'
		    						,labelWidth:70
		    						,allowBlank:false
		    					},
		    					{
		    						xtype:'textfield'
		    						,width:285
		    						,style:'margin:10px 0px 0px 0px'
		    						,fieldLabel:'dni'
		    						,name:'dni'
		    						,id:'dni'
		    						,labelWidth:70
		    					},
		    					{
		    						xtype:'checkboxfield'
		    						,width:180
		    						,style:'margin:10px 0px 0px 20px'
		    						,fieldLabel:'Usuario Administrador'
		    						,id:'desParametro1'
		    						,labelWidth:150
		    					},
		    					{
						            xtype: 'checkboxgroup',
						            labelWidth:70,
						            width:575,
						            id:'chkGroupOpciones',
						            style:'margin:10px 0px 0px 0px',
						            fieldLabel: 'Opciones',
						            // Distribute controls across 3 even columns, filling each row
						            // from left to right before starting the next row
						            columns: 2
						        }
		    			]
		    			
		    		}	
		    ],
		    buttons:[
		    		{
		    			text:'Grabar',
		    			handler:function(){

		    				var form = Ext.getCmp('form-usuario').getForm();
		    				if(form.isValid()){

		    					var thisWin = Ext.getCmp('win-usuarios');
		    					
		    					thisWin.body.mask('Espere un momento por favor.');
		    					
		    					var usuario = form.getValues();
		    					var c = 0;
		    					Ext.each(Ext.getCmp('chkGroupOpciones').items.items,function(ite){
		    					
		    						if(ite.checked){
		    							usuario['opciones['+c+'].idOpcion'] = ite.inputValue;
		    							c++;
		    						}
		    						
		    					})
		    					
		    					if(Ext.getCmp('desParametro1').checked){
		    						usuario.desParametro1 = 'S';
		    					}else{
		    						usuario.desParametro1 = 'N';
		    					}
		    					
		    					Util.runAjax({
										url:PATH_PROYECTO_BASE+'cierre-calles/usuario-registro',
										async : true,
										method : 'POST',
										params:usuario,
										success:function(http){
											var response = Ext.decode(http.responseText);
											
											thisWin.body.unmask();

											UsuarioService.buscar();
											
											thisWin.close();
											
										}
									});
		    					
		    				}
		    				
		    				
		    			}
		    		}
		    		,
		    		{
		    			text:'Cancelar',
		    			handler:function(){
		    			
		    				Ext.getCmp('win-usuarios').close();
		    				
		    			}
		    		}
		    ]
		}).show();
		
	}
	,
	buscar:function(){
	
		var grid = Ext.getCmp('grid-usuarios');
		var store = grid.store;
		
		var params = {};
		
		if(!Ext.isEmpty(Ext.getCmp('txt-usuario').getValue())){
			params.desLoginUser	= Ext.getCmp('txt-usuario').getValue();
		}
		
		if(!Ext.isEmpty(Ext.getCmp('txt-nombres').getValue())){
			params.desNombreCompleto	= Ext.getCmp('txt-nombres').getValue();
		}
		
		if(!Ext.isEmpty(Ext.getCmp('txt-dni').getValue())){
			params.dni	= Ext.getCmp('txt-dni').getValue();
		}
		
		store.proxy.extraParams = params;
		
		store.load();
		
		
	},
	buildGridUsuario:function(){
	
			Ext.define('Usuario', {
			     extend: 'Ext.data.Model',
			     fields: ['ideUsuario','ideEstado', 'desLoginUser', 'inHabilitado','desNombreCompleto','email','dni']
			 });
			 
			 
			var store = Ext.create('Ext.data.Store', {
					autoLoad : true,
					model : 'Usuario',
					proxy : {
						type : 'ajax',
						url:PATH_PROYECTO_BASE+'cierre-calles/usuarios-list',
						actionMethods : 'POST',
						reader : {
							root : 'data'
						},
						simpleSortMode : true
						,timeout:99999999
					}
				});
				
				
			var grid = Ext.create('Ext.grid.Panel', {
			    store: store,
			    renderTo:'render',
			    title:'Administración de Usuarios',
			    id:'grid-usuarios',
			    border:true,
			    columns: [
			        { text: 'Usuario',  dataIndex: 'desLoginUser' , flex:1  },
			        { text: 'Nombres',  dataIndex: 'desNombreCompleto' , flex:1 },
			        { text: 'Email',  dataIndex: 'email' , flex:1 },
			        { text: 'Dni',  dataIndex: 'dni' , width:120 },
			        {
							            xtype:'actioncolumn',
							       		width:50,
							            align:'center',
							            items: [{
							            	
							                icon: PATH_PROYECTO_BASE+'images/delete_16.png',
							                tooltip: 'Eliminar',
							                handler: function(grid, rowIndex, colIndex) {
							                    var record = grid.getStore().getAt(rowIndex);
							                    
												Ext.Msg.confirm('Confirmar', '¿Seguro de eliminar el usuario seleccionado?', function(btn, text){
												    if (btn == 'yes'){
												        
												    	Util.runAjax({
																	url:PATH_PROYECTO_BASE+'cierre-calles/usuario-eliminar',
																	params:{
																		idUsuario:record.data.ideUsuario
																	},
																	async : true,
																	method : 'POST',
																	success:function(http){
																		var response = Ext.decode(http.responseText);
																		
																		UsuarioService.buscar();
										                        		
																	}
																});
												    	
												    }
												});
							                    
							                    
							                }
							            }]
			} 
							        
			    ],
			    height: $(window).height()-30,
			    width:$(window).width()-30
			 
			    ,dockedItems : {
			            xtype: 'toolbar',
			            items: [
			            {
			            	xtype:'textfield',
			            	emptyText:'Usuario',
			            	id:'txt-usuario',
			            	listeners:{
			            		specialkey : function(f, e) {
									if (e.getKey() == e.ENTER) {
										UsuarioService.buscar();
									}
								}
			            	}
			            },
			            {
			            	xtype:'textfield',
			            	emptyText:'Nombres',
			            	id:'txt-nombres',
			            	listeners:{
			            		specialkey : function(f, e) {
									if (e.getKey() == e.ENTER) {
										UsuarioService.buscar();
									}
								}
			            	}
			            },
			            {
			            	xtype:'textfield',
			            	emptyText:'Dni',
			            	id:'txt-dni',
			            	listeners:{
			            		specialkey : function(f, e) {
									if (e.getKey() == e.ENTER) {
										UsuarioService.buscar();
									}
								}
			            	}
			            	
			            },
			            {
	                    iconCls:'btnBuscarSmall'
	                    ,handler:function(){
		                    	UsuarioService.buscar();
		                    }	
		                },
			              '->',
			                {
			            	  	text:'Nuevo'
			                    ,id:'btnAddGeo'
			                    ,iconCls:'btnAddSmall'
//			                    ,style:'border-right:0px; border-left:0px; border-top:0px; border-bottom:1px #DBDBDB solid;'
			                    ,handler:function(){
			                    	UsuarioService.registroUsuario({
			                    		title:'Registrar Usuario'
			                    	});
			                    }
			                }
			                ,
									{
										iconCls : 'btnEditSmall',
										text:'Editar',
										id:'btnEditar',
										disabled:true,
										handler : function() {
											
											var record = Ext.getCmp('grid-usuarios').getSelectionModel().getSelection();
											
											UsuarioService.registroUsuario({
					                    		title:'Editar Usuario',
					                    		ideUsuario: record[0].data.ideUsuario
					                    	});
										}
									},
									{
            xtype:'splitbutton',
            text: DES_LOGIN,
            iconCls: 'btnUser16',
            menu: [
            		{
            			text: 'Ver Mapa',
            			iconCls: 'btnMap16',
            			href:PATH_PROYECTO_BASE+'cierre-calles/mapa'
            		}
            		,{
            			text: 'Cerrar Sesion',
            			iconCls: 'btnLogout16',
            			href:PATH_PROYECTO_BASE+'log-out'
            		}
            ]
        }
			                    
			            ]
			        }
			});
			
			grid.on('select',function(){
			
				if(Ext.getCmp('btnEditar')){
					Ext.getCmp('btnEditar').setDisabled(false);
				}
				
			});
			
			store.on('load',function(){
				if(Ext.getCmp('btnEditar')){
					Ext.getCmp('btnEditar').setDisabled(true);
				}
				
			});
			 
		
	}
	
}


window.onload = function(){

	UsuarioService.init();
	
}
