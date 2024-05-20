-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录', '2085', '1', 'storeRecord', 'store/storeRecord/index', 1, 0, 'C', '0', '0', 'store:storeRecord:list', '#', 'admin', sysdate(), '', null, '商店订单记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'store:storeRecord:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'store:storeRecord:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'store:storeRecord:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'store:storeRecord:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('商店订单记录导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'store:storeRecord:export',       '#', 'admin', sysdate(), '', null, '');