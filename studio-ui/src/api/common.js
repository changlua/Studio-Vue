import { getToken } from '@/utils/auth'

export default {
  "uploadAction": process.env.BASE_URL + '/zf/api/upload',
  "uploadFilesAction": process.env.BASE_URL + '/zf/api/upload/files'
};
