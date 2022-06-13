/**
 * 判断是否为空
 * @param {} str
 * @returns boolean
 */
const isEmpty = function (str) {
  if (str === undefined || str === '' || str.trim() === '') {
    return true
  }
  return false
}

export { isEmpty }
