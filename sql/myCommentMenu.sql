-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论', '2085', '1', 'myComment', 'store/myComment/index', 1, 0, 'C', '0', '0', 'store:myComment:list', '#', 'admin', sysdate(), '', null, '我的评论菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'store:myComment:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'store:myComment:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'store:myComment:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'store:myComment:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('我的评论导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'store:myComment:export',       '#', 'admin', sysdate(), '', null, '');