'use client'
import { useParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function () {

    const param = useParams();
    const [data, setData] = useState({});
    const [content, setContent] = useState("");
    const router = useRouter();

    useEffect(() => {
        getData();
    }, [])

    const getData = async () => {
        fetch('http://localhost:8080/api/v1/datas/' + param.id)
            .then(result => result.json())
            .then(resourse => setData(resourse.data));
    }

    const dataValue = event => {
        console.log(event.target.value);
        setContent(event.target.value);
    }

    const updateData = async (event) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/api/v1/datas/' + param.id, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content
            })
        })
        if (response.ok) {
            console.log(response);
            getData();
        } else {
            console.log(response);
        }
    }

    const deleteData = async () => {
        const response = await fetch('http://localhost:8080/api/v1/datas/' + param.id, {
            method: "DELETE"
        });
        if (response.ok) {
            router.push("/datas");
        } else {
            console.log(response);
        }
    }
    return (
        <>
            <ul>
                <li>ID : {data.id}</li>
                <li>내용 : {data.content}</li>
                <li>작성일 : {data.createdDate}</li>
                <li>수정일 : {data.modifiedDate}</li>
            </ul>
            <form>
                <span>수정하기</span>
                <input type="text" onChange={dataValue} />
                <button onClick={updateData}>수정</button>
            </form>

            <button onClick={deleteData}>Data 삭제</button>
        </>
    );
}