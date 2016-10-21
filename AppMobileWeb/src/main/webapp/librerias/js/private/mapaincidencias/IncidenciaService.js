Ext.ns('IncidenciaService');

IncidenciaService = {

	init:function(){
		this.buildGridIncidencia();
	}
	,
	setZonaSubZona:function(punto){
	
				    					Util.runAjax({
										url:PATH_PROYECTO_BASE+'catastro/get-sector-zona-by-cordenada',
										async : true,
										method : 'POST',
										params:{punto:punto},
										success:function(http){
											var response = Ext.decode(http.responseText);
											
											//console.log(response);
											if(response.data && response.data.nombresector){
												Ext.getCmp('zona').setValue(response.data.nombresector);
											}else{
												Ext.getCmp('zona').setValue('');
											}
											
											if(response.data && response.data.codi_zona){
												Ext.getCmp('subZona').setValue(response.data.codi_zona);
											}else{
												Ext.getCmp('subZona').setValue('');
											}
											
											
										}
									});
	
	},
	openFormRegistro:function(options){
		
		options = options || {};
		
		//console.log('options');
		//console.log(options);
		var wWin  = 740;
		var hWin  = 660;
		var bwWin = wWin-25;
		
		
		var estadosUnidInter = Ext.create('Ext.data.Store', {
			    fields: ['name', 'value'],
			    data : [
			        {"value":"9", "name":"AMBULANCIA"},
			        {"value":"3", "name":"BICICLETA"},
			        {"value":"4", "name":"BRIGADA CANINA"},
			        {"value":"5", "name":"BRIGADA PLAYERA"},
			        {"value":"6", "name":"CÁMARA DE VIDEO"},
			        {"value":"2", "name":"MOTORIZADO"},
			        {"value":"8", "name":"OPERADOR"},
			        {"value":"7", "name":"SERVICIO TÁCTICO"},
			        {"value":"1", "name":"UNIDAD MÓVIL"}
			    ]
			});
			
			var estadosCalifStore = Ext.create('Ext.data.Store', {
			    fields: ['name', 'value'],
			    data : [
			        {"value":"1", "name":"NUEVO"},
			        {"value":"4", "name":"ATENDIDO"},
			        {"value":"5", "name":"ATENDIDO/FALSA ALARMA"},
			        {"value":"2", "name":"DESCARTADO"},
			        {"value":"3", "name":"REPETIDO"}
			    ]
			});
			
			var cbxEstadosCalifStore = Ext.create('Ext.form.ComboBox', {
			    store: estadosCalifStore,
			    queryMode: 'local',
			    id:'cbxEstadosCalif',
			    forceSelection:true,
			    displayField: 'name',
			    fieldLabel: 'Califique el caso',
			   	width : (bwWin/2)-60,
			   	labelWidth : 100, 
			    valueField: 'value',
			    name:'estadoCalifVoxiva',
			    allowBlank:false,
			    value:'1',
			    listeners:{
			    
			    	select:function(f){
			    	
			    		if(f.getValue()==4 || f.getValue()==5){
			    			Ext.getCmp('fechaHoraAtencionVoxiva').setDisabled(false);
							Ext.getCmp('horaAtencion').setDisabled(false);
			    		}else{
			    			Ext.getCmp('fechaHoraAtencionVoxiva').reset();
							Ext.getCmp('horaAtencion').reset();
			    			Ext.getCmp('fechaHoraAtencionVoxiva').setDisabled(true);
							Ext.getCmp('horaAtencion').setDisabled(true);
			    		}
			    		
			    	}
			    	
			    }
			}); 
			
			var siNoStore = Ext.create('Ext.data.Store', {
			    fields: ['name', 'value'],
			    data : [
			        {"value":"S", "name":"SI"},
			        {"value":"N", "name":"NO"}
			    ]
			});
			
			var mediosStore = Ext.create('Ext.data.Store', {
			    fields: ['name', 'value'],
			    data : [
			        {"value":9831, "name":"ALERTA MIRAFLORES"},
			        {"value":399, "name":"FACEBOOK"},
			        {"value":9832, "name":"WHATSAPP"},
			        {"value":400, "name":"TWITTER"},
			        {"value":314, "name":"LLAMADA TELEFÓNICA"}
			    ]
			});
			
			var cbxMedios = Ext.create('Ext.form.ComboBox', {
			    store: mediosStore,
			    queryMode: 'local',
			    id:'medioIngreso',
			    forceSelection:true,
			    displayField: 'name',
			    fieldLabel: 'Medio ingreso',
			   	width : (bwWin),
			   	labelWidth : 100, 
			   	style:'margin-bottom:10px;',
			    valueField: 'value',
			    name:'medioIngreso',
			    allowBlank:false,
			    listeners:{
			    
			    	select:function(f){
			    	
			    	}
			    	
			    }
			}); 
			
			var cbxImportante = Ext.create('Ext.form.ComboBox', {
			    store: siNoStore,
			    queryMode: 'local',
			    id:'cbxImportante',
			     labelStyle:'text-align:right;',
			    forceSelection:true,
			    displayField: 'name',
			    fieldLabel: '¿Importante?',
			   	width : (bwWin/4)+20,
			   	labelWidth : 100, 
			   	allowBlank:false,
			   	style:'margin-bottom:10px;',
			    valueField: 'value',
			    value:'S',
			    name:'importanteVoxiva',
			    listeners:{
			    
			    	select:function(f){
			    	
			    	}
			    	
			    }
			}); 
			
			var cbxLlamadaDevuelta = Ext.create('Ext.form.ComboBox', {
			    store: siNoStore,
			    queryMode: 'local',
			    id:'cbxLlamadaDevuelta',
			    forceSelection:true,
			    style:'margin-bottom:10px;',
			    displayField: 'name',
			    allowBlank:false,
			     labelStyle:'text-align:right;',
			    fieldLabel: '¿Llamada devuelta?',
			   	width : (bwWin/4)+42,
			   	labelWidth : 130, 
			    valueField: 'value',
			    value:'S',
			    name:'llamadaDevueltaVoxiva',
			    listeners:{
			    
			    	select:function(f){
			    	
			    	}
			    	
			    }
			});
			
			var cbxUnidInterv = Ext.create('Ext.form.ComboBox', {
			    store: estadosUnidInter,
			    queryMode: 'local',
			    id:'unidIntervVoxiva',
			    name:'unidIntervVoxiva',
			    forceSelection:true,
			    style:'margin-bottom:10px;',
			    displayField: 'name',
			    fieldLabel: 'U. Interviniente',
			   	width : (bwWin/2),
			   	labelWidth : 100, 
			    valueField: 'value',
			    listeners:{
			    
			    	select:function(f){
			    	
			    	}
			    	
			    }
			});
			
			
			
		Ext.create('Ext.window.Window', {
		    title: options.title,
		    height: hWin,
		    width: wWin,
		    modal:true,
		    id:'win-form-registro',
		    layout: 'column',
		    bodyStyle:'padding:10px 10px 0px 10px;',
		    items: [
		    		{
		    			xtype:'form',
		    			layout: 'column',
		    			width:bwWin,
		    			id:'form-registro-incidencia',
		    			listeners:{
		    			
		    				afterrender:function(){
		    				
		    					if(options.idIncidencia){
			    					var thisWin = Ext.getCmp('win-form-registro');
			    					
			    					thisWin.body.mask('Obteniendo información...');
			    					
			    					
			    					Util.runAjax({
											url:PATH_PROYECTO_BASE+'mapa-incidencia/get-geometria-by-id-incidencia',
											async : true,
											method : 'GET',
											params:{idIncidencia:options.idIncidencia},
											success:function(http){
											
												try{
												
													
												var response = Ext.decode(http.responseText);
												//console.log(response);	
												var data = response.data;
												
												Ext.getCmp('medioIngreso').setValue(data.medioIngreso);
												
												Ext.getCmp('tipoCasoVoxiva').setValue(data.tipoCasoVoxiva);
												
												var tipoCaso = Ext.getCmp('tipoCasoVoxiva').displayTplData[0];
												
												//console.log(tipoCaso);	
			            	  		
						            	  		var storeSubTipoCaso = Ext.getCmp('cbxSubTipoCaso').store;
						            	  		storeSubTipoCaso.removeAll();
						            	  		if(tipoCaso){
						            	  		storeSubTipoCaso.load({
						            	  								params:{
						            	  										idGrupo:tipoCaso.ideGrupoElemento
						            	  										}
						            	  										,
						            	  								callback:function(){
						            	  									if(storeSubTipoCaso.count()>0){
						            	  										Ext.getCmp('cbxSubTipoCaso').setDisabled(false);
						            	  										Ext.getCmp('cbxSubTipoCaso').setValue(data.subTipoCasoVoxiva);
						            	  										
						            	  										var subTipoCaso = Ext.getCmp('cbxSubTipoCaso').displayTplData[0];
						            	  									
							            	  									if(subTipoCaso.referencia2 == '1'){
															            	  		Ext.getCmp('cbxEstado').setDisabled(false);
															            	  		Ext.getCmp('cbxEstado').setValue(data.estadoVoxiva);
															            	  		
															            	  		if(data.estadoVoxiva){
			            	  		
															            	  			var estadoCaso = Ext.getCmp('cbxEstado').displayTplData[0];
															            	  			
																            	  		var storeSubEstado = Ext.getCmp('cbxSubEstado').store;
																            	  		storeSubEstado.removeAll();
																            	  		
																            	  		storeSubEstado.load({
												            	  								params:{
												            	  										idGrupo:estadoCaso.ideGrupoElemento
												            	  										}
												            	  										,
												            	  								callback:function(){
												            	  									
												            	  									Ext.getCmp('cbxSubEstado').setValue(data.subEstadoVoxiva);
												            	  									
												            	  									if(storeSubEstado.count()>0){
												            	  										Ext.getCmp('cbxSubEstado').setDisabled(false);
												            	  									}else{
												            	  										Ext.getCmp('cbxSubEstado').setDisabled(true);
												            	  									}
												            	  									
												            	  									thisWin.body.unmask();
												            	  								}		
												            	  							});
																								            	  		
												            	  							
			            	  							
															            	  		}else{
															            	  			thisWin.body.unmask();
															            	  		}
															            	  		
														            	  		}else{
														            	  			Ext.getCmp('cbxEstado').setDisabled(true);
														            	  			thisWin.body.unmask();
														            	  		}
						            	  										
						            	  									}else{
						            	  										thisWin.body.unmask();
						            	  										Ext.getCmp('cbxSubTipoCaso').setDisabled(true);
						            	  									}
						            	  									
						            	  								}		
			            	  							});
											}else{
												thisWin.body.unmask();
											}
												
			            	  					Ext.getCmp('cbxEstadosCalif').setValue(data.estadoCalifVoxiva);
			            	  					Ext.getCmp('cbxImportante').setValue(data.importanteVoxiva);
			            	  					Ext.getCmp('cbxLlamadaDevuelta').setValue(data.llamadaDevueltaVoxiva);
			            	  					Ext.getCmp('nombreReportaIncidencia').setValue(data.nombreReportaIncidencia);
			            	  					Ext.getCmp('telefReportaIncidencia').setValue(data.telefReportaIncidencia);
			            	  					Ext.getCmp('direccionIncidencia').setValue(data.direccionIncidencia);
			            	  					Ext.getCmp('incidenciaPresentada').setValue(data.incidenciaPresentada);
			            	  					Ext.getCmp('accionesIncidencia').setValue(data.accionesIncidencia);
			            	  					Ext.getCmp('unidIntervVoxiva').setValue(data.unidIntervVoxiva);
			            	  					Ext.getCmp('desUnidIntervVoxiva').setValue(data.desUnidIntervVoxiva);
			            	  					Ext.getCmp('nombreRecepcionaIncidencia').setValue(data.nombreRecepcionaIncidencia);
												Ext.getCmp('dniRecepcionaIncidencia').setValue(data.dniRecepcionaIncidencia);
												Ext.getCmp('cuadraEvento').setValue(data.cuadraEvento);
												
			            	  					
												if(data.horaLlamadaVoxiva){
													var horaLlamadaVoxiva = Ext.Date.parse(data.horaLlamadaVoxiva, "d-m-Y H:i");
													Ext.getCmp('horaLlamadaVoxiva').setValue(horaLlamadaVoxiva);
													Ext.getCmp('horaIncidencia').setValue(horaLlamadaVoxiva);
												}
												
												if(data.fechaHoraAtencionVoxiva && (data.estadoCalifVoxiva+''=='4' || data.estadoCalifVoxiva+''=='5')){
													var fechaHoraAtencionVoxiva = Ext.Date.parse(data.fechaHoraAtencionVoxiva, "d-m-Y H:i");
													Ext.getCmp('fechaHoraAtencionVoxiva').setValue(fechaHoraAtencionVoxiva);
													Ext.getCmp('horaAtencion').setValue(fechaHoraAtencionVoxiva);
												}else{
													Ext.getCmp('fechaHoraAtencionVoxiva').setDisabled(true);
													Ext.getCmp('horaAtencion').setDisabled(true);
												}
												
												var detalleGeometria = data.detalleGeometria;
												
												if(detalleGeometria && detalleGeometria.length>0){
												
													Ext.getCmp('cordenadas').setValue(detalleGeometria[0].geometria);
													IncidenciaService.setZonaSubZona(detalleGeometria[0].geometria.split(' ')[1]+detalleGeometria[0].geometria.split(' ')[0]);
													
												}
												
												if(Ext.getCmp('idIncidencia')){
													Ext.getCmp('idIncidencia').destroy();
												}
												
												Ext.getCmp('form-registro-incidencia').add(
																			{
																		        xtype: 'hiddenfield',
																		        name: 'idIncidencia',
																		        id:'idIncidencia',
																		        value: data.idIncidencia
																	   		}
											    );
												

			            	  					
											
													
												}catch(e){
												
												thisWin.close();
												}
											
											
											}
										});
										
			    				}
		    					
		    				}
		    			
		    			},
		    			items:[
		    			
		    			cbxMedios
		    			,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin-200)
		    						,fieldLabel:'Cordenadas'
		    						,labelWidth : 100
		    						,name:'cordenadas'
		    						,style:'margin-bottom:10px;'
		    						,id:'cordenadas'
		    						,allowBlank:false
		    						,readOnly:true
		    					}
		    					,
		    					{
		    						xtype:'button'
		    						,width:200
		    						,text:'Establecer Cordenadas'
		    						,handler:function(){
				    					//Uso del componenete -->
		    							
					                		Util.windowGeoreferenciaPunto(
					                		{
					                			width:600,
					                			height:600
					                			,idCanvasMap:'mapa-incidencia-geo'
					                			,idCordenadas:'cordenadas'
					                			,fnGrabar:function(punto,direccionData){
					                				
					                				//console.log('direccionData');
					                				//console.log(direccionData);
					                				
					                				Ext.getCmp('cordenadas').setValue(punto.lat+' '+punto.lng);
					                				
//					                				if(direccionData && direccionData.toponimia){
//					                					Ext.getCmp('direccionIncidencia').setValue(direccionData.toponimia); 
//					                				}
					                				
					                				 $.ajax({
					     							    url: PATH_PROYECTO_BASE+"catastro/get-name-street",
					     							    dataType: "json",
					     							    data:{latitud:punto.lat,longitud:punto.lng},
					     							    success: function( response ) {
					     							    	
					     							    	console.log(response);
					     							    	var result = response.result || [];
					     							    	if(result.length>0){
					     							    		
					     							    		var name = result[0];
					     							    		
					     							    		Ext.getCmp('direccionIncidencia').setValue(name.names[1]?name.names[1]:name.names[0]);
					     							    	}
					     							    	
					     							    }
					     							   
					     							  }
					     						  );
					                				
					                				
					                				IncidenciaService.setZonaSubZona(punto.lng+' '+punto.lat);
					                				
					                			}
					                		}	
					                		
					                		);
				                		//<-- end uso componente
		    							
		    						}
		    					}
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin/2)
		    						,fieldLabel:'Zona'
		    						,labelWidth : 100
		    						,name:'zona'
		    						,maxHeight:20 
		    						,readOnly:true
		    						,style:'margin-bottom:10px;'
		    						,id:'zona'
		    						,allowBlank:true
		    					}
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin/2)
		    						,fieldLabel:'Sub-zona'
		    						,labelWidth : 103
		    						,name:'subZona'
		    						,style:'margin-bottom:10px;'
		    						,id:'subZona'
		    						,readOnly:true
		    						,allowBlank:true
		    						,labelStyle:'text-align:right;'
		    					}
		    			,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin-150)
		    						,fieldLabel:'Dirección'
		    						,labelWidth : 100
		    						,name:'direccionIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'direccionIncidencia'
		    						,allowBlank:true
		    					}
		    					,
		    					
		    						{
		    						xtype:'textfield'
		    						,width:(150)
		    						,fieldLabel:'Número'
		    						,labelWidth : 60
		    						,labelStyle:'text-align:right;'
		    						,name:'cuadraEvento'
		    						,style:'margin-bottom:10px;'
		    						,id:'cuadraEvento'
		    						,allowBlank:true
		    					}
		    			,
		    			{
		    			
							   id:'tipoCasoVoxiva',
							   allowBlank:false,
							   xtype:'ComboGeneric',
							   width:(bwWin/2),
							   labelWidth : 100, 
							   extraParams:{
							   				identificador:'VOX_CASO'
							   },
			            	   root:'data',
			            	   autoLoad:true,
			            	   fieldLabel: 'Tipo de caso',
			            	   url:PATH_PROYECTO_BASE+'catalogo/get-grupo-voxiva',
			            	   queryMode: 'local',
			            	   valueField:'ref1',
			            	   displayField :'displayValue',
			            	   forceSelection:true,
			            	   style:'margin-bottom:10px;',
			            	   name:'tipoCasoVoxiva',
			            	   model:
			            		   {
			            		   	nameModel:'CalleGeo'
			            		   	,fields:['ideGrupoElemento','ideGrupoElement1','desNombre','clvAbreviarGrupo','ref1','codEstado'
			            		   	,{
			            		   		name:'displayValue',
			            		   		mapping:'desNombre',
			            		   		convert:function(v,record){
			            		   			return v.split('_')[1];
			            		   		}
			            		   	}
			            		   	]	
			            		   }
			            	  ,listeners:{
			            	  	select:function(f,record){
			            	  		
			            	  		Ext.getCmp('cbxSubTipoCaso').reset();
			            	  		
			            	  		var storeSubTipoCaso = Ext.getCmp('cbxSubTipoCaso').store;
			            	  		storeSubTipoCaso.removeAll();
			            	  		
			            	  		Ext.getCmp('cbxEstado').reset();
			            	  		Ext.getCmp('cbxEstado').setDisabled(true);
			            	  		
			            	  		Ext.getCmp('cbxSubEstado').reset();
			            	  		Ext.getCmp('cbxSubEstado').setDisabled(true);
			            	  		
			            	  		storeSubTipoCaso.load({
			            	  								params:{
			            	  										idGrupo:record.data.ideGrupoElemento
			            	  										}
			            	  										,
			            	  								callback:function(){
			            	  									if(storeSubTipoCaso.count()>0){
			            	  										Ext.getCmp('cbxSubTipoCaso').setDisabled(false);
			            	  									}else{
			            	  										Ext.getCmp('cbxSubTipoCaso').setDisabled(true);
			            	  									}
			            	  								}		
			            	  							});
			            	  		
			            	  	}
			            	
			            	  }	   
						
		    			}
		    			,
		    			{
		    			
							   id:'cbxSubTipoCaso',
							   allowBlank:false,
							   xtype:'ComboGeneric',
							   width:(bwWin/2),
							   labelWidth : 130,
							   disabled:true,
							   labelStyle:'text-align:right;',
							   extraParams:{
							   				idGrupo:'-1'
							   },
			            	   root:'data',
			            	   autoLoad:true,
			            	   fieldLabel: 'Sub-tipo de caso',
			            	   url: PATH_PROYECTO_BASE+"catalogo/get-catalogo-by-grupo",
			            	   queryMode: 'local',
			            	   valueField:'referencia',
			            	   displayField :'displayValue',
			            	   forceSelection:true,
			            	   style:'margin-bottom:10px;',
			            	   name:'subTipoCasoVoxiva',
			            	   model:
			            		   {
			            		   	nameModel:'CalleGeo'
			            		   	,fields:['referencia','ideElemento','desNombre'
			            		   	,{
			            		   		name:'displayValue',
			            		   		mapping:'desNombre',
			            		   		convert:function(v,record){
			            		   			return v;
			            		   		}
			            		   	}
			            		   	]	
			            		   }
			            	  ,listeners:{
			            	  	select:function(f,record){
			            	  		            	  		Ext.getCmp('cbxEstado').reset();
			            	  		if(record.data.referencia2 == '1'){
				            	  		Ext.getCmp('cbxEstado').setDisabled(false);
			            	  		}else{
			            	  			Ext.getCmp('cbxEstado').setDisabled(true);
			            	  		}
			            	  		
			            	  	}
			            	
			            	  }	   
						
		    			}
		    			,
		    			{
		    				
		    			
							   id:'cbxEstado',
							   allowBlank:false,
							   xtype:'ComboGeneric',
							   width:(bwWin/2),
							   labelWidth : 100, 
							   extraParams:{
							   				identificador:'VOX_ESTADO'
							   },
			            	   root:'data',
			            	   autoLoad:true,
			            	   disabled:true,
			            	   fieldLabel: 'Estado',
			            	   url:PATH_PROYECTO_BASE+'catalogo/get-grupo-voxiva',
			            	   queryMode: 'local',
			            	   valueField:'ref1',
			            	   displayField :'displayValue',
			            	   forceSelection:true,
			            	   style:'margin-bottom:10px;',
			            	   name:'estadoVoxiva',
			            	   model:
			            		   {
			            		   	nameModel:'CalleGeo'
			            		   	,fields:['ideGrupoElemento','ideGrupoElement1','desNombre','clvAbreviarGrupo','ref1','codEstado'
			            		   	,{
			            		   		name:'displayValue',
			            		   		mapping:'desNombre',
			            		   		convert:function(v,record){
			            		   			return v.split('_')[1];
			            		   		}
			            		   	}
			            		   	]	
			            		   }
			            	  ,listeners:{
			            	  	select:function(f,record){
			            	  		
			            	  		Ext.getCmp('cbxSubEstado').reset();
			            	  		
			            	  		var storeSubEstado = Ext.getCmp('cbxSubEstado').store;
			            	  		storeSubEstado.removeAll();
			            	  		
			            	  		storeSubEstado.load({
			            	  								params:{
			            	  										idGrupo:record.data.ideGrupoElemento
			            	  										}
			            	  										,
			            	  								callback:function(){
			            	  									if(storeSubEstado.count()>0){
			            	  										Ext.getCmp('cbxSubEstado').setDisabled(false);
			            	  									}else{
			            	  										Ext.getCmp('cbxSubEstado').setDisabled(true);
			            	  									}
			            	  								}		
			            	  							});
			            	  		
			            	  	}
			            	
			            	  }	   
		    			
		    			}
		    			,
		    			{
		    			
							   id:'cbxSubEstado',
							   allowBlank:false,
							   xtype:'ComboGeneric',
							   width:(bwWin/2),
							   labelWidth : 130,
							   disabled:true,
							   name:'subEstadoVoxiva',
							   labelStyle:'text-align:right;',
							   extraParams:{
							   				idGrupo:'-1'
							   },
			            	   root:'data',
			            	   autoLoad:true,
			            	   fieldLabel: 'Sub-estado',
			            	   url: PATH_PROYECTO_BASE+"catalogo/get-catalogo-by-grupo",
			            	   queryMode: 'local',
			            	   valueField:'referencia',
			            	   displayField :'displayValue',
			            	   forceSelection:true,
			            	   style:'margin-bottom:10px;',
			            	   model:
			            		   {
			            		   	nameModel:'CalleGeo'
			            		   	,fields:['referencia','ideElemento','desNombre'
			            		   	,{
			            		   		name:'displayValue',
			            		   		mapping:'desNombre',
			            		   		convert:function(v,record){
			            		   			return v;
			            		   		}
			            		   	}
			            		   	]	
			            		   }
			            	  ,listeners:{
			            	  	select:function(f,record){
			            	  	}
			            	
			            	  }	   
						
		    			}
		    			,
		    			cbxEstadosCalifStore
		    			,
		    			cbxImportante
		    			,
		    			cbxLlamadaDevuelta,
		    			
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin-220)
		    						,fieldLabel:'Reportante'
		    						,labelWidth : 100
		    						,name:'nombreReportaIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'nombreReportaIncidencia'
		    						,allowBlank:false
		    					}
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(220)
		    						,fieldLabel:'Telef. Reportante'
		    						,labelWidth : 110
		    						,name:'telefReportaIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'telefReportaIncidencia'
		    						,labelStyle:'text-align:right;'
		    						,allowBlank:true
		    					}
		    					
		    					,
		    					{
		    						xtype:'datefield'
		    						,width:255
		    						,fieldLabel:'Fecha Incidencia'
		    						,labelWidth : 100
		    						,format: 'd-m-Y'
		    						,emptyText:'DD-MM-YYYY'
		    						,name:'horaLlamadaVoxiva'
		    						,style:'margin-bottom:10px;'
		    						,id:'horaLlamadaVoxiva'
		    						,allowBlank:false
		    					}
		    					,
		    					{
		    						xtype:'timefield'
		    						,width:90
		    						,hideTrigger:true
		    						,format:'H:i'
		    						,name:'horaIncidencia'
		    						,style:'margin-bottom:10px;margin-left:10px;'
		    						,id:'horaIncidencia'
		    						,emptyText:'HH:MM'
		    						,allowBlank:false
		    						,labelStyle:'text-align:right;'
		    					}
		    					,
		    					{
		    						xtype:'datefield'
		    						,width:260
		    						,fieldLabel:'Fecha Atención'
		    						,labelWidth : 105
		    						,name:'fechaHoraAtencionVoxiva'
		    						,format: 'd-m-Y'
		    						,emptyText:'DD-MM-YYYY'
		    						,style:'margin-bottom:10px;'
		    						,id:'fechaHoraAtencionVoxiva'
		    						,allowBlank:false
		    						,labelStyle:'text-align:right;'
		    					}
		    					,
		    					{
		    						xtype:'timefield'
		    						,width:90
		    						,hideTrigger:true
		    						,format:'H:i'
		    						,emptyText:'HH:MM'
		    						,name:'horaAtencion'
		    						,style:'margin-bottom:10px;margin-left:10px;'
		    						,id:'horaAtencion'
		    						,allowBlank:false
		    						,labelStyle:'text-align:right;'
		    					}
		    					,
		    					{
		    						xtype:'textarea'
		    						,width:(bwWin/2)
		    						,fieldLabel:'Incidencia presentada'
		    						,labelWidth : 100
		    						,name:'incidenciaPresentada'
		    						,maxHeight:20 
		    						,style:'margin-bottom:10px;'
		    						,id:'incidenciaPresentada'
		    						,allowBlank:false
		    					}
		    					,
		    					{
		    						xtype:'textarea'
		    						,width:(bwWin/2)
		    						,fieldLabel:'Acción Tomada'
		    						,labelWidth : 103
		    						,name:'accionesIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'accionesIncidencia'
		    						,allowBlank:true
		    						,labelStyle:'text-align:right;'
		    					}
		    					,
		    					cbxUnidInterv
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin/2)
		    						,fieldLabel:'Nom. Unidad'
		    						,labelWidth : 103
		    						,name:'desUnidIntervVoxiva'
		    						,style:'margin-bottom:10px;'
		    						,id:'desUnidIntervVoxiva'
		    						,allowBlank:true
		    						,labelStyle:'text-align:right;'
		    					}
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(bwWin-250)
		    						,fieldLabel:'Ope. a cargo'
		    						,labelWidth : 100
		    						,name:'nombreRecepcionaIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'nombreRecepcionaIncidencia'
		    						,allowBlank:true
		    					}
		    					,
		    					{
		    						xtype:'textfield'
		    						,width:(250)
		    						,fieldLabel:'Dni Operador'
		    						,labelWidth : 100
		    						,name:'dniRecepcionaIncidencia'
		    						,style:'margin-bottom:10px;'
		    						,id:'dniRecepcionaIncidencia'
		    						,labelStyle:'text-align:right;'
		    						,allowBlank:true
		    					}
		    					
		    			]
		    			
		    		}	
		    ],
		    buttons:[
		    		{
		    			text:'Grabar',
		    			handler:function(){

		    				var form = Ext.getCmp('form-registro-incidencia').getForm();
		    				if(form.isValid()){

		    					var thisWin = Ext.getCmp('win-form-registro');
		    					
		    					thisWin.body.mask('Espere un momento por favor.');
		    					
		    					var incidencia = form.getValues();
		    					
		    					
		    					if(!Ext.isEmpty(incidencia.horaLlamadaVoxiva)){
		    						incidencia.horaLlamadaVoxiva = incidencia.horaLlamadaVoxiva+' '+incidencia.horaIncidencia;
		    					}
		    					
		    					if(!Ext.isEmpty(incidencia.fechaHoraAtencionVoxiva)){
		    						incidencia.fechaHoraAtencionVoxiva = incidencia.fechaHoraAtencionVoxiva+' '+incidencia.horaAtencion;
		    					}
		    					
		    					if(!Ext.isEmpty(incidencia.cordenadas)){
		    						incidencia.latitud = incidencia.cordenadas.split(' ')[0];
		    						incidencia.longitud = incidencia.cordenadas.split(' ')[1];
		    					}
		    					
		    				
		    					
								incidencia.desEstadoVoxiva = Ext.getCmp('cbxEstado').getRawValue();
								incidencia.desSubEstadoVoxiva = Ext.getCmp('cbxSubEstado').getRawValue();
								incidencia.desEstadoCalifVoxiva = Ext.getCmp('cbxEstadosCalif').getRawValue();
								incidencia.direccionFinal = Ext.getCmp('tipoCasoVoxiva').getRawValue();
		    					
		    					Util.runAjax({
										url:PATH_PROYECTO_BASE+'mapa-incidencia/registrar-form-mapa',
										async : true,
										method : 'POST',
										params:incidencia,
										success:function(http){
											var response = Ext.decode(http.responseText);
											
											thisWin.body.unmask();

											IncidenciaService.buscar();
											
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
		    			
		    				Ext.getCmp('win-form-registro').close();
		    				
		    			}
		    		}
		    ]
		}).show();
		
	}
	,
	buscar:function(){
	
		var grid = Ext.getCmp('grid-incidencias');
		var store = grid.store;
		
		var params = {};
		
		//listEstadoCalifica
		
		if(!Ext.isEmpty(Ext.getCmp('txt-incidencia').getValue())){
			params.incidenciaPresentada	= Ext.getCmp('txt-incidencia').getValue();
		}
		
		if(!Ext.isEmpty(Ext.getCmp('txt-fecha').getValue())){
			params.horaLlamadaVoxiva	= Ext.getCmp('txt-fecha').getValue();
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cbxEstadosCalifStore').getValue())){
			//params.dni	= Ext.getCmp('txt-dni').getValue();
			Ext.each(Ext.getCmp('cbxEstadosCalifStore').getValue(),function(value,i){
				params['listEstadoCalifica['+i+']'] = value;
			});
		}
		
		if(!Ext.isEmpty(Ext.getCmp('medioIngresoSearch').getValue())){
			//params.dni	= Ext.getCmp('txt-dni').getValue();
			Ext.each(Ext.getCmp('medioIngresoSearch').getValue(),function(value,j){
				params['listmedios['+j+']'] = value;
			});
		}
		
		store.proxy.extraParams = params;
		store.load();
		
		
	},
	buildGridIncidencia:function(){
	
			Ext.define('IncidenciaGeneric', {
			     extend: 'Ext.data.Model',
			fields: ['accionesIncidencia','calle','cuadraEvento','desCallles','desTipoIncidencia','descripcion','detalleGeometria','direccionFinal','direccionIncidencia'
			         ,'dniRecepcionaIncidencia','estado','fecHoraFin','fecHoraInicio','horaLlamadaVoxiva','idCasoVoxiva','idDispositivo','idEvento','idIncidencia','idPreRegistro','idTipoVia'
			         ,'idTipoViaCatastro','idUsuarioSesion','idVia','idViaCatastro','incidenciaPresentada','latitud','lineas','longitud','medioIngreso','nombreCalleFin','nombreCalleInicio','nombreRecepcionaIncidencia'
			         ,'nombreReportaIncidencia','nombreTipoEvento','nroCasoVoxiva','severidad','strFecFin','strFecIni','subTipo','subTipoCasoVoxiva','telefReportaIncidencia','tipo','tipoCasoVoxiva','tipoCierre','tipoDireccion'
			         ,'tipoEvento','tipoIncidencia','visible', 'accionesIncidencia',{
			         	name:'desMedioIngreso'
						,mapping:'desMedioIngreso'
						,convert:function(v){
						
							if(v=='VOXIVA_MAP'){
								v= 'VOXIVA';
							}
							
							return v;
							
						}
			         }]
			
			 });
			 
			var estadosCalifStore = Ext.create('Ext.data.Store', {
			    fields: ['name', 'value'],
			    data : [
			        {"value":"0", "name":"CREADO"},
			        {"value":"1", "name":"NUEVO"},
			        {"value":"4", "name":"ATENDIDO"},
			        {"value":"5", "name":"ATENDIDO/FALSA ALARMA"},
			        {"value":"2", "name":"DESCARTADO"},
			        {"value":"3", "name":"REPETIDO"}
			    ]
			});
			
			var cbxEstadosCalifStore = Ext.create('Ext.form.ComboBox', {
			    store: estadosCalifStore,
			    queryMode: 'local',
			    id:'cbxEstadosCalifStore',
			    forceSelection:true,
			    displayField: 'name',
			   	width : 190,
			   	emptyText:'Estado',
			    valueField: 'value',
			    multiSelect:true,
			    listeners:{
			    
			    	change:function(f){
			    		IncidenciaService.buscar();
			    	}
			    	
			    }
			}); 
			 
			
			var mediosStore = Ext.create('Ext.data.Store', {
		    fields: ['name', 'value'],
		    data : [
		        {"value":9831, "name":"VOXIVA"},
		        {"value":399, "name":"FACEBOOK"},
		        {"value":9832, "name":"WHATSAPP"},
		        {"value":9841, "name":"WAZE"}
		    ]
			});
			
			var cbxMedios = Ext.create('Ext.form.ComboBox', {
			    store: mediosStore,
			    queryMode: 'local',
			    id:'medioIngresoSearch',
			    forceSelection:true,
			    displayField: 'name',
			    width:150,
			    emptyText:'Medio de Ingreso',
			    valueField: 'value',
			    name:'medioIngreso',
			    multiSelect:true,
			    listeners:{
			    	change:function(f){
			    		IncidenciaService.buscar();
			    	}
			    }
			}); 
			 
			var store = Ext.create('Ext.data.Store', {
					autoLoad : true,
					model : 'IncidenciaGeneric',
					pageSize: 20,
					proxy : {
						type : 'ajax',
						url:PATH_PROYECTO_BASE+'mapa-incidencia/incidencias-list-generic',
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
			    title:'Administración de Incidencias',
			    id:'grid-incidencias',
			    border:true,
			    columns: [
			        { text: 'Id Incidencia',  dataIndex: 'idIncidencia' ,hidden:true },
			        { text: 'Medio Ingreso',  dataIndex: 'desMedioIngreso', width : 120   },
			        { text: 'Estado',  dataIndex: 'desEstadoCalifVoxiva', width : 180   },
			        { text: 'Fecha de Incidencia',  dataIndex: 'horaLlamadaVoxiva' , width : 150 },
			        { text: 'Incidencia Presentada',  dataIndex: 'incidenciaPresentada' , flex : 1},
			        { text: 'Acción Tomada',  dataIndex: 'accionesIncidencia' , flex : 1},
			        {
							            xtype:'actioncolumn',
							       		width:50,
							            align:'center',
							            items: [{
							            	
							                icon: PATH_PROYECTO_BASE+'images/map_icon_16.png',
							                tooltip: 'Ubicación',
							                handler: function(grid, rowIndex, colIndex) {
							                    var record = grid.getStore().getAt(rowIndex);
							                    
							                    
							                    Ext.create('Ext.window.Window', {
													    title: 'Ubicación Geográfica',
													    height: 450,
													    width: 500,
													    modal:true,
													    layout: 'fit',
													    html:'<div id="canvas-map-ug" style="width:496px;height:404px;"></div>',
													    listeners:{
													    
													    	afterrender:function(){
													    	
													    		var me = this;
													    		
													    		me.body.mask('Espere un moomento por favor.');
													    		
													  			Util.runAjax({
																	url:PATH_PROYECTO_BASE+'mapa-incidencia/get-geometria-by-id-incidencia',
																	params:{
																		idIncidencia:record.data.idIncidencia
																	},
																	async : true,
																	method : 'POST',
																	success:function(http){
																		var response = Ext.decode(http.responseText);
																			
										                        		me.body.unmask();
										                        		var data = response.data;
										                        		
										                        		var detalleGeometria = {};
										                        		
										                        		if(data.detalleGeometria && data.detalleGeometria[0]){
										                        		
										                        			detalleGeometria = data.detalleGeometria[0]
										                        			var geometria = detalleGeometria.geometria.split(' ');
										                        			
													                          var point = {lat: parseFloat(geometria[0]), lng: parseFloat(geometria[1])};
																			  var map = new google.maps.Map(document.getElementById('canvas-map-ug'), {
																			    zoom: 20,
																			    center: point
																			  });
																			
																			  var contentString = data.direccionIncidencia;
																			
																			  var infowindow = new google.maps.InfoWindow({
																			    content: contentString
																			  });
																			
																			  var marker = new google.maps.Marker({
																			    position: point,
																			    map: map
																			  });
																			  marker.addListener('click', function() {
																			  	if(data.direccionIncidencia){
																			    	infowindow.open(map, marker);
																			  	}
																			  });
										                        			
										                        		}
										                        		
																		
																	}
																});
													    		
													    	}
													    	
													    }
													}).show();
																				                    
							                    
							                    
							                }
							            }]
			        }
			        ,
			        {
							            xtype:'actioncolumn',
							       		width:50,
							            align:'center',
							            items: [{
							            	
							                icon: PATH_PROYECTO_BASE+'images/delete_16.png',
							                tooltip: 'Eliminar',
							                handler: function(grid, rowIndex, colIndex) {
							                    var record = grid.getStore().getAt(rowIndex);
							                    
												Ext.Msg.confirm('Confirmar', '¿Seguro de eliminar el registro seleccionado?', function(btn, text){
												    if (btn == 'yes'){
												        
												    	Util.runAjax({
																	url:PATH_PROYECTO_BASE+'mapa-incidencia/eliminar-incidencia',
																	params:{
																		idIncidencia:record.data.idIncidencia
																	},
																	async : true,
																	method : 'POST',
																	success:function(http){
																		var response = Ext.decode(http.responseText);
																		
																		IncidenciaService.buscar();
										                        		
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
			 
			    ,dockedItems : [
			                    
						{
						    xtype: 'pagingtoolbar',
						    store: store,   // same store GridPanel is using
						    dock: 'bottom',
						    displayInfo: true
						},
			                    
			                    {
			            xtype: 'toolbar',
			            items: [
			            
			            cbxMedios,
			            
			            cbxEstadosCalifStore,
			            
			            {
			            	xtype:'textfield',
			            	emptyText:'Incidencia',
			            	id:'txt-incidencia',
			            	listeners:{
			            		specialkey : function(f, e) {
									if (e.getKey() == e.ENTER) {
										IncidenciaService.buscar();
									}
								}
			            	}
			            },
			            {
			            	xtype:'textfield',
			            	emptyText:'DD-MM-YYYY HH:MM',
			            	id:'txt-fecha',
			            	listeners:{
			            		specialkey : function(f, e) {
									if (e.getKey() == e.ENTER) {
										IncidenciaService.buscar();
									}
								}
			            	}
			            	
			            },
			            {
	                    iconCls:'btnBuscarSmall'
	                    ,handler:function(){
		                    	IncidenciaService.buscar();
		                    }	
		                },
			              '->',
			               {
			            	  	text:'Nuevo'
			                    ,id:'btnAddGeo'
			                    ,iconCls:'btnAddSmall'
//			                    ,style:'border-right:0px; border-left:0px; border-top:0px; border-bottom:1px #DBDBDB solid;'
			                    ,handler:function(){
			                    	IncidenciaService.openFormRegistro({
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
											
											var record = Ext.getCmp('grid-incidencias').getSelectionModel().getSelection();
											
											//console.log("record");
											//console.log(record);
											
											IncidenciaService.openFormRegistro({
					                    		title:'Editar Inicidencia',
					                    		idIncidencia: record[0].data.idIncidencia
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
			        }]
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

	IncidenciaService.init();
	
}
