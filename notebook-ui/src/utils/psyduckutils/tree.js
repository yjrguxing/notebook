/**
 * 数组数据转树形数据
 * @param {Array} data 数组数据
 * @param {String} id id字段名
 * @param {String} fatherId 父id字段名
 * @returns {*[]|*}
 */
export function arrayToTree(data, id = 'id', fatherId = 'fatherId') {
  const result = []
  if (!Array.isArray(data)) {
    return result
  }
  // 找到所有fatherId为0的节点
  const topNodes = data.filter(item => item[fatherId] === 0)
  // 递归找到子节点
  const findChildren = (nodes) => {
    for (const node of nodes) {
      node.children = data.filter(item => item[fatherId] == node[id])
      findChildren(node.children)
    }
    return nodes;
  }
  // 递归找到子节点
  const nodes = findChildren(topNodes);
  return nodes;
}
