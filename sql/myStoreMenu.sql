-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店', '2085', '1', 'myStore', 'store/myStore/index', 1, 0, 'C', '0', '0', 'store:myStore:list', '#', 'admin', sysdate(), '', null, '我的商店菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'store:myStore:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'store:myStore:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'store:myStore:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'store:myStore:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的商店导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'store:myStore:export',       '#', 'admin', sysdate(), '', null, '');