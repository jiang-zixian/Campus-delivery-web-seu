-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单', '2185', '1', 'takeOrders', 'rider/takeOrders/index', 1, 0, 'C', '0', '0', 'rider:takeOrders:list', '#', 'admin', sysdate(), '', null, '我要接单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'rider:takeOrders:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'rider:takeOrders:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'rider:takeOrders:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'rider:takeOrders:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我要接单导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'rider:takeOrders:export',       '#', 'admin', sysdate(), '', null, '');