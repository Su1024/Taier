/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.batch.mapstruct.vo;

import com.dtstack.batch.domain.BatchResource;
import com.dtstack.batch.dto.BatchResourceAddDTO;
import com.dtstack.batch.dto.BatchResourceDTO;
import com.dtstack.batch.vo.BatchResourceVO;
import com.dtstack.batch.web.pager.PageResult;
import com.dtstack.batch.web.resource.vo.query.BatchResourceAddVO;
import com.dtstack.batch.web.resource.vo.query.BatchResourcePageQueryVO;
import com.dtstack.batch.web.resource.vo.result.BatchGetResourceByIdResultVO;
import com.dtstack.batch.web.resource.vo.result.BatchGetResourcesResultVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BatchResourceMapstructTransfer {

    BatchResourceMapstructTransfer INSTANCE = Mappers.getMapper(BatchResourceMapstructTransfer.class);

    /**
     * BatchResourcePageQueryVO --> BatchResourceDTO
     *
     * @param engineVO
     * @return
     */
    BatchResourceDTO resourceVOToResourceDTO(BatchResourcePageQueryVO engineVO);

    /**
     * BatchResourceAddVO --> BatchResourceAddDTO
     *
     * @param batchResourceAddVO
     * @return batchResourceAddDTO
     */
    BatchResourceAddDTO resourceVOToResourceAddDTO(BatchResourceAddVO batchResourceAddVO);

    /**
     * List<BatchResource> --> List<BatchGetResourcesResultVO>
     *
     * @param batchResource
     * @return
     */
    List<BatchGetResourcesResultVO> batchResourceListToBatchGetResourcesResultVOList(List<BatchResource> batchResource);

    /**
     * BatchResourceVO --> BatchGetResourceByIdResultVO
     *
     * @param batchResourceVO
     * @return
     */
    BatchGetResourceByIdResultVO batchResourceVOToBatchGetResourceByIdResultVO(BatchResourceVO batchResourceVO);

    /**
     * BatchResource --> BatchGetResourcesResultVO
     *
     * @param batchResource
     * @return
     */
    BatchGetResourcesResultVO batchResourceToBatchGetResourcesResultVO(BatchResource batchResource);

    /**
     * PageResult<List<BatchResourceVO>> --> ageResult<List<BatchGetResourceByIdResultVO>>
     *
     * @param pageResult
     * @return batchResourceAddDTO
     */
    PageResult<List<BatchGetResourceByIdResultVO>> pageResultToBatchGetResourceByIdResultVOPageResult(PageResult<List<BatchResourceVO>> pageResult);
}