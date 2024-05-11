-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表', '2106', '1', 'itemList', 'itemList/itemList/index', 1, 0, 'C', '0', '0', 'itemList:itemList:list', '#', 'admin', sysdate(), '', null, '商品列表菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'itemList:itemList:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'itemList:itemList:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'itemList:itemList:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'itemList:itemList:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商品列表导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'itemList:itemList:export',       '#', 'admin', sysdate(), '', null, '');